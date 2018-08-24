package com.concurrent.coll013.queue;

import java.util.concurrent.DelayQueue;

/**
 * 网吧
 *
 * @author dd
 */
public class CyberBar implements Runnable {

    private DelayQueue<Netizen> queue = new DelayQueue<>();

    public boolean work = true;

    public void start(String name, String id, int money) {
        Netizen man = new Netizen(name, id, 1000 * money + System.currentTimeMillis());
        System.out.println("网名" + man.getName() + " 身份证" + man.getId() + "交钱" + money + "块,开始上机...");
        this.queue.add(man);
    }

    public void end(Netizen man) {
        System.out.println("网名" + man.getName() + " 身份证" + man.getId() + "时间到下机...");
    }

    @Override
    public void run() {
        while (work) {
            try {
                Netizen man = queue.take();
                end(man);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        try {
            System.out.println("网吧开始营业");
            CyberBar cyberBar = new CyberBar();

            Thread thread = new Thread(cyberBar);
            thread.start();

            cyberBar.start("路人甲", "123", 3);
            cyberBar.start("路人乙", "234", 10);
            cyberBar.start("路人丙", "345", 5);
            System.out.println("---------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}  