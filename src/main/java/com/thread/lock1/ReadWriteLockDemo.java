package com.thread.lock1;

import com.thread.pool.MyThreadFactory;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author dab
 * @version 1.0.0
 * @Description : 2.1 读写锁的基本用法
 * <p>
 * 锁 —> 读锁、写锁，读锁与读锁不互斥，读锁与写锁互斥，写锁与写锁互斥，这是由jvm自己控制的。
 * <p>
 * 这很好理解，读嘛，大家都能读，不会对数据造成修改，只要涉及到写，那就可能出问题。
 * <p>
 * 我们写代码的时候只要在正确的位置上相应的锁即可。
 * <p>
 * 读写锁有个接口叫ReadWriteLock，我们可以创建具体的读写锁实例，通过读写锁也可以拿到读锁和写锁。
 * @Date 2018/5/23 16:15
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {

        /* 封装共享的数据、读写锁和待执行的任务的类*/
        final Queue3 q3 = new Queue3();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), new MyThreadFactory());

        for (int i = 0; i < 3; i++) {

            // 开启三个线程写数据
            Runnable thread1 = () -> {
                q3.put(new Random().nextInt(100));
            };

            // 开启三个线程读数据
            Runnable thread2 = q3::get;

            threadPoolExecutor.execute(thread1);
            threadPoolExecutor.execute(thread2);
        }
    }
}

class Queue3 {
    /**
     * 共享的数据
     */
    private Object data = null;

    /**
     * 定义读写锁
     */
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    /**
     * 读取数据的任务方法
     */
    void get() {
        rwl.readLock().lock(); // 上读锁
        try {
            /* 读之前打印数据显示 */
            System.out.println(Thread.currentThread().getName() + ":before read: " + data);

            Thread.sleep((long) new Random().nextInt(1000));

            /* 读之后打印数据显示 */
            System.out.println(Thread.currentThread().getName() + ":after read: " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();// 释放读锁
        }
    }


    /**
     * 写数据的任务方法
     */
    void put(Object data) {
        rwl.writeLock().lock(); // 上写锁
        try {
            //写之前打印数据显示
            System.out.println(Thread.currentThread().getName() + ":before write: " + this.data);

            Thread.sleep((long) new Random().nextInt(1000));

            //写数据
            this.data = data;

            //写之后打印数据显示
            System.out.println(Thread.currentThread().getName() + ":after write: " + this.data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();// 释放写锁
        }
    }
}


