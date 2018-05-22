package com.thread;

/**
 * @Description :
 * @Author : pangd
 * @Date 2018/5/19 22:31
 */
public class TicketThread extends Thread {

    private int ticket = 10;

    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                if (this.ticket > 0) {
                    try {
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName() + "卖票---->" + (this.ticket--));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] arg) {
        TicketThread t1 = new TicketThread();
        new Thread(t1, "线程1").start();
        new Thread(t1, "线程2").start();
    }
}
