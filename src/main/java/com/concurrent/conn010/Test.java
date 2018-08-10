package com.concurrent.conn010;

/**
 * <P> </p>
 *
 * @author dab
 * @version 1.0.0
 * @date 2018/8/10 16:09
 */
public class Test {

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
