package com.concurrent.conn010;

/**
 * @author PC-20170417-666
 *
 * ThreadLocal : 线程局部变量，是一种多线程间并发访问变量的解决方案。
 */
public class ConnThreadLocal {

    private static ThreadLocal<String> th = new ThreadLocal<>();

    private void setTh(String value) {
        th.set(value);
    }

    private void getTh() {
        System.out.println(Thread.currentThread().getName() + ":" + th.get());
    }

    public static void main(String[] args) {

        final ConnThreadLocal connThreadLocal = new ConnThreadLocal();

        Thread t1 = new Thread(() -> {
            connThreadLocal.setTh("张三");
            connThreadLocal.getTh();
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                connThreadLocal.getTh();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
