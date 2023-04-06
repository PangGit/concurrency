package com.thread.alternate;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁方法
 *
 * @author pang
 * @since 2023/4/6 下午 10:32
 */
public class DemoLock {

    /**
     * 通过JDK5中的Lock锁来保证线程的访问的互斥
     */
    private static final Lock LOCK = new ReentrantLock();

    /**
     * 通过state的值来确定是否打印
     */
    private static int state = 0;

    public static void main(String[] args) throws InterruptedException {
        new ThreadA().start();
        Thread.sleep(10);

        new ThreadB().start();
        Thread.sleep(10);

        new ThreadC().start();
        Thread.sleep(10);
    }

    private static void extracted(int x, String s) {
        for (int i = 0; i < 10; ) {
            LOCK.lock();
            try {
                while (state % 3 == x) {
                    state++;
                    System.out.print(s + " " + state + "\n");
                    i++;
                }
            } finally {
                // unlock()操作必须放在finally块中
                LOCK.unlock();
            }
        }
    }

    private static class ThreadA extends Thread {
        @Override
        public void run() {
            extracted(0, "A");
        }
    }

    private static class ThreadB extends Thread {
        @Override
        public void run() {
            extracted(1, "B");
        }
    }

    private static class ThreadC extends Thread {
        @Override
        public void run() {
            extracted(2, "C");
        }
    }
}