package com.concurrent.coll013_queue;

import java.util.concurrent.DelayQueue;

public class WangBa implements Runnable {

    private DelayQueue<Wangmin> queue = new DelayQueue<>();

    public boolean work = true;

    public void start(String name, String id, int money) {
        Wangmin man = new Wangmin(name, id, 1000 * money + System.currentTimeMillis());
        System.out.println("网名" + man.getName() + " 身份证" + man.getId() + "交钱" + money + "块,开始上机...");
        this.queue.add(man);
    }

    public void end(Wangmin man) {
        System.out.println("网名" + man.getName() + " 身份证" + man.getId() + "时间到下机...");
    }

    @Override
    public void run() {
        while (work) {
            try {
                Wangmin man = queue.take();
                end(man);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        try {
            System.out.println("网吧开始营业");
            WangBa wangBa = new WangBa();

            Thread thread = new Thread(wangBa);
            thread.start();

            wangBa.start("路人甲", "123", 1);
            wangBa.start("路人乙", "234", 10);
            wangBa.start("路人丙", "345", 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}  