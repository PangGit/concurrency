package com.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description :
 * @Author : pangd
 * @Date 2018/6/5 22:52
 */
public class ThreadsPoolFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 无返还值
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " run");
            Thread.currentThread().setUncaughtExceptionHandler((Thread t, Throwable e) -> {
                System.out.println(t.getName() + ": " + e.getMessage());
            });
            throw new RuntimeException("this is a exception!");
        };

        AtomicInteger atomicInteger = new AtomicInteger(0);
        // 有返回值
        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " run");
            Thread.sleep(100L);
            return method(atomicInteger);
        };

        /* 核心线程数为6，最大线程数为10。超时时间为5秒。线程数大于核心线程数，新开线程，核心线程默认情况下不会被回收，不受超时时间限制。*/
        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new MyThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );

        Future<String> future0 = null;
        for (int j = 0; j < 6; j++) {
            future0 = executor.submit(callable);
        }
        Future<?> future = executor.submit(runnable);

        try {
            System.out.println("callable:" + future0.get());
            System.out.println("runnable:" + future.get());
        } catch (InterruptedException e) {
            // 处理中断异常
            System.out.println("处理中断异常");
        } catch (ExecutionException e) {
            // 处理无法执行任务异常
            System.out.println("处理无法执行任务异常:" + e.getMessage());
        } finally {
            System.out.println("CorePoolSize:" + executor.getCorePoolSize());
            System.out.println("PoolSize:" + executor.getPoolSize());
            System.out.println("QueueSize:" + executor.getQueue().size());
            // 关闭线程池
            executor.shutdownNow();
            System.out.println("-----关闭线程池------");
        }
    }

    private static String method(AtomicInteger atomicInteger) {
        return String.valueOf(atomicInteger.incrementAndGet());
    }


}
