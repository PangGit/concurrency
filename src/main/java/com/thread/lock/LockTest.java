package com.thread.lock;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dab
 * @version 1.0.0
 * @Description :
 * @Date 2018/5/22 16:24
 */
public class LockTest {

    public static void main(String[] args) {
            new LockTest().init();
    }

    private void init() {
        Outputer outputer = new Outputer();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        // 线程1
        Runnable myRunnable1 = () -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outputer.output("xiancheng1");
        };

        // 线程2
        Runnable myRunnable2 = () -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outputer.output("xiancheng2");
        };

        executor.execute(myRunnable1);
        executor.execute(myRunnable2);
    }

    /**
     * 自定义一个类，保存锁和待执行的任务
     */
     class Outputer {
        /**
         * 字符串打印方法，一个个字符的打印
         */
        public void output(String name) {
            /**
             * 定义一个锁，Lock是个接口，需实例化一个具体的Lock
             */
            Lock lock = new ReentrantLock();
            lock.lock();
            try {
                int len = name.length();
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println("");
            } finally {
                lock.unlock(); //try起来的原因是万一一个线程进去了然后挂了或者抛异常了，那么这个锁根本没有释放
            }
        }
    }

}