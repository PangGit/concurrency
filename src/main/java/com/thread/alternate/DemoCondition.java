package com.thread.alternate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 结合 Condition
 *
 * @author pang
 * @since 2023/4/6 下午 11:14
 */
public class DemoCondition {

    private static final Lock LOCK = new ReentrantLock();

    private static final Condition A = LOCK.newCondition();
    private static final Condition B = LOCK.newCondition();
    private static final Condition C = LOCK.newCondition();

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        new ThreadA().start();
        Thread.sleep(10);

        new ThreadB().start();
        Thread.sleep(10);

        new ThreadC().start();
        Thread.sleep(10);
    }

    private static void extracted(int x, String s, Condition current, Condition next) {
        LOCK.lock();
        try {
            for (int i = 0; i < 10; i++) {
                // 注意这里是 != x，也就是说在 count % 3 == x 之前，当前线程一直阻塞状态
                while (count % 3 != x) {
                    // current释放lock锁
                    current.await();
                }
                count++;
                System.out.print(s + " " + count + "\n");
                // current 执行完唤醒 next 线程
                next.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    private static class ThreadA extends Thread {
        @Override
        public void run() {
            extracted(0, "A", A, B);
        }
    }

    private static class ThreadB extends Thread {
        @Override
        public void run() {
            extracted(1, "B", B, C);
        }
    }

    private static class ThreadC extends Thread {
        @Override
        public void run() {
            extracted(2, "C", C, A);
        }
    }
}