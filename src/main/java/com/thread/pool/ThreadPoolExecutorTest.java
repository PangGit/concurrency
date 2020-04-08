package com.thread.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池ThreadPoolExecutor构造方法和规则
 * <p>
 * 构造方法
 * <p>
 * pool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue);
 * pool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory);
 * pool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler);
 * pool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)
 * <p>
 * corePoolSize     核心线程数，默认情况下核心线程会一直存活，即使处于闲置状态也不会受存keepAliveTime限制。除非将allowCoreThreadTimeOut设置为true。
 * maximumPoolSize  线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
 * keepAliveTime    非核心线程的闲置超时时间，超过这个时间就会被回收。
 * unit             指定keepAliveTime的单位，如TimeUnit.SECONDS。当将allowCoreThreadTimeOut设置为true时对corePoolSize生效。
 * workQueue        线程池中的任务队列.常用的有三种队列，SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue。
 * threadFactory    线程工厂，提供创建新线程的功能。ThreadFactory是一个接口，只有一个方法
 * <p>
 * public interface ThreadFactory {
 * Thread newThread(Runnable r);
 * }
 * 通过线程工厂可以对线程的一些属性进行定制。
 * <p>
 * RejectedExecutionHandler也是一个接口，只有一个方法
 * <p>
 * public interface RejectedExecutionHandler {
 * void rejectedExecution(Runnable var1, pool var2);
 * }
 * <p>
 * 当线程池中的资源已经全部使用，添加新线程被拒绝时，会调用 RejectedExecutionHandler 的 rejectedExecution 方法。
 * <p>
 *
 * @date 2018/5/23 9:53
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        Runnable myRunnable = () -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        /* 1 核心线程数为6，最大线程数为10。超时时间为5秒。
         * 可以看到每个任务都是是直接启动一个核心线程来执行任务，一共创建了6个线程，不会放入队列中。
         * 8秒后线程池还是6个线程，核心线程默认情况下不会被回收，不受超时时间限制。*/
        // ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        /* 2 核心线程数为3，最大线程数为6。超时时间为5秒,队列是LinkedBlockingDeque。
         * 当任务数超过核心线程数时，会将超出的任务放在队列中，只会创建3个线程重复利用。*/
        // ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

        /* 3 核心线程数为3，最大线程数为6。超时时间为5秒,队列是SynchronousQueue.
         * 当队列是SynchronousQueue时，超出核心线程的任务会创建新的线程来执行，看到一共有6个线程。
         * 但是这些线程是非核心线程，受超时时间限制，在任务完成后超过5秒就会被回收。
         * 所以最后看到线程池还是只有三个线程。*/
        // ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        /* 4 核心线程数是3，最大线程数是4，队列是LinkedBlockingDeque.
         * 当任务队列是LinkedBlockingDeque，会将超过核心线程的任务放在任务队列中排队。
         * LinkedBlockingDeque根本不受最大线程数影响。
         * 但是当LinkedBlockingDeque有大小限制时就会受最大线程数影响了 */
        // ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

        /* 4.1 将队列大小设置为2 .
         * 首先为三个任务开启了三个核心线程1，2，3，然后第四个任务和第五个任务加入到队列中，
         * 第六个任务因为队列满了，就直接创建一个新线程4，这是一共有四个线程，没有超过最大线程数。
         * 8秒后，非核心线程收超时时间影响回收了，因此线程池只剩3个线程了。*/
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(2), new MyThreadFactory());

        /* 4.2 将队列大小设置为1.
         * 直接出错在第6个execute方法上。因为核心线程是3个，当加入第四个任务的时候，就把第四个放在队列中。
         * 加入第五个任务时，因为队列满了，就创建新线程执行，创建了线程4。
         * 当加入第六个线程时，也会尝试创建线程，但是因为已经达到了线程池最大线程数，所以直接抛异常了。*/
        // ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1));

        /* 5 核心线程数是3 ，最大线程数是4，队列是SynchronousQueue,
         * 当队列是SynchronousQueue时，超出核心线程的任务会创建新的线程来执行，
         * 加入第4个任务时，线程池满了，
         * 加入第5个线程时，也会尝试创建线程，但是因为已经达到了线程池最大线程数，所以直接抛异常了。*/
        // ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        // 拒绝策略
        // ThreadPoolExecutor.AbortPolicy      丢弃任务，抛出RejectedExecutionException异常（默认）。
        // ThreadPoolExecutor.DiscardPolicy    丢弃任务，不抛出异常。
        // ThreadPoolExecutor.DiscardOldestPolicy   丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
        // ThreadPoolExecutor.CallerRunsPolicy      由调用线程处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);

        System.out.println("---先开三个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());

        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);

        System.out.println("---再开三个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("----8秒之后----");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());

    }


}
