package com.thread.pool;

import java.util.Calendar;
import java.util.concurrent.*;

/**
 * @Description :
 * @Author : pangd
 * @Date 2018/6/5 22:52
 */
public class ThreadsPoolFuture {

    public static void main(String[] args) {

        Runnable runnable = () -> {
            try {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + " run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        /**1 核心线程数为6，最大线程数为10。超时时间为5秒。
         * 可以看到每个任务都是是直接启动一个核心线程来执行任务，一共创建了6个线程，不会放入队列中。
         * 8秒后线程池还是6个线程，核心线程默认情况下不会被回收，不受超时时间限制。*/
        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new MyThreadFactory());

        Future<?> future = executor.submit(runnable);

        try {
            Object s = future.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            // 处理中断异常
            System.out.println("处理中断异常");
        } catch (ExecutionException e) {
            // 处理无法执行任务异常
            System.out.println("处理无法执行任务异常");
        } finally {
            // 关闭线程池
            executor.shutdown();
            System.out.println("-----关闭线程池------");
        }


    }


}
