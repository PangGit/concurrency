package com.concurrent.conn009;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description : 使用wait / notify 模拟 queue。
 */
public class MyQueue {

    /*1,元素集合*/
    private LinkedList<Object> list = new LinkedList<>();
    /*2，计数器*/
    private AtomicInteger count = new AtomicInteger(0);
    /*3，上下限*/
    private final int minSize = 0;
    private final int maxSize;

    /*4，构造方法：赋值上限*/
    public MyQueue(int size) {
        this.maxSize = size;
    }

    /*5，初始化一个对象锁*/
    private final Object lock = new Object();

    /*6,put(Object) : 把object加到BlockQueue中，如果没有空间，则调用该方法的线程被阻断，直到有空间再继续。*/
    public void put(Object obj) {
        synchronized (lock) {
            while (count.get() == this.maxSize) {
                try {
                    /* Causes the current thread to wait until another thread invokes the
                     * {@link java.lang.Object#notify()} method or the
                     * {@link java.lang.Object#notifyAll()} method for this object.
                     * */
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Appends the specified element to the end of this list.
            list.add(obj);
            // Atomically increments by one the current value.
            count.incrementAndGet();
            // Wakes start a single thread that is waiting on this object's monitor.
            lock.notify();

            System.out.println("add an object : " + obj);
        }
    }

    /*7,take(Object): 取走BlockingQueue里排在首位的对象，若队列为空，阻断进入等待状态，直到队列有线数据加入。*/
    public void take() {
        Object object;
        synchronized (lock) {
            while (count.get() == this.minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Removes and returns the first element from this list.
            object = list.removeFirst();
            // Atomically decrements by one the current value.
            count.decrementAndGet();
            //  Wakes start a single thread that is waiting on this object's monitor.
            lock.notify();

            // Prints a String and then terminate the line.
            System.out.println("remove the first object : " + object);
        }
    }


    public static void main(String[] args) {

        final MyQueue myQueue = new MyQueue(5);
        myQueue.put("a");
        myQueue.put("b");
        myQueue.put("c");
        myQueue.put("d");
        myQueue.put("e");

        System.out.println("queue size : " + myQueue.list.size());

        Thread t1 = new Thread(() -> {
            myQueue.put("f");
            myQueue.put("g");
        }, "t1");

        t1.start();

        Thread t2 = new Thread(() -> {
            myQueue.take();
            myQueue.take();
        }, "t2");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myQueue.list.forEach(System.out::println);
    }


}
