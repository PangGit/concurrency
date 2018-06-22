package com.concurrent.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 线程1添加至5个线程时，线程2停止。
 */
public class ThreadVolatile {

    private volatile static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    list.add("abc");
                    System.out.println("currentThread ：" + Thread.currentThread().getName() + " add an element " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            while (true) {
                if (list.size() == 5) {
                    System.out.println("currentThread ：" + Thread.currentThread().getName() + "， list size = 5 ,  currentThread stop .");
                    break;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}