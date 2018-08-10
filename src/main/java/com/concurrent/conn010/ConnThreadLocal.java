package com.concurrent.conn010;

/**
 * @author PC-20170417-666
 * <p>
 * ThreadLocal : 线程局部变量，是一种多线程间并发访问变量的解决方案。
 */
public class ConnThreadLocal {

    public static ThreadLocal<String> th = new ThreadLocal<>();

    public void setTh(String value) {
        th.set(value);
    }

    public void getTh() {
        System.out.println(Thread.currentThread().getName() + ":" + th.get());
    }

}
