package com.concurrent.conn008;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * wait:等待，释放锁；
 * notify：唤醒单个线程，不释放锁；
 */
public class CountDownLatchTest {

    private volatile static List<String> list = new ArrayList<>();

    public static void main(String[] args) {

        // 实例化出来一个 lock , 当使用 wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
        //final Object lock = new Object();

        /**
         * CountDownLatch 类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。
         * public void await() throws InterruptedException { };   //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
         * public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };  //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
         * public void countDown() { };  //将count值减1
         * */
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t1 = new Thread(() -> {
            try {
//                synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    list.add(String.valueOf(i));
                    System.out.println("currentThread ：" + Thread.currentThread().getName() + " add an element..");
                    Thread.sleep(500);
                    if (list.size() == 5) {
                        System.out.println("t1 send notify..");
                        //lock.notify();
                        countDownLatch.countDown();
                    }
                }
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            //synchronized (lock) {
            if (list.size() != 5) {
                try {
                    System.out.println("t2 enter...");
                    //lock.wait();
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("currentThread ：" + Thread.currentThread().getName() + "  end..");
            //}
        }, "t2");

        t2.start();
        t1.start();

    }

}
