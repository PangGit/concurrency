package com.thread.lock;

import com.thread.pool.MyThreadFactory;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author dab
 * @version 1.0.0
 * @Description : 读写锁用于缓存数据
 * <p>
 * 即模拟缓存数据。
 * <p>
 * 实现的功能如下：现在有5个线程都需要拿数据，一开始是没有数据的，所以最先去拿数据的那个线程发现没数据，它就得去初始化一个数据，然后其他线程拿数据的时候就可以直接拿了。
 * @Date 2018/5/23 16:57
 */
public class ReadWriteLockTest2 {

    public static void main(String[] args) {

        CacheData cache = new CacheData();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 5, TimeUnit.SECONDS, new SynchronousQueue<>(true), new MyThreadFactory());

        int max = 5;
        for (int i = 1; i <= max; i++) {
            //都去拿数据
            Runnable thread = cache::processCache;

            executor.execute(thread);
        }
    }
}

class CacheData {

    /**
     * 需要缓存的数据
     */
    private Object data = null;
    /**
     * 用来标记是否有缓存数据
     */
    private boolean cacheValid;
    /**
     * 定义读写锁
     */
    private ReadWriteLock rwl = new ReentrantReadWriteLock();


    /**
     * @Description :
     * @param: []
     * @return: void
     */
    void processCache() {

        rwl.readLock().lock(); //上读锁

        /* 如果没有缓存，那说明是第一次访问，需要给data赋个值 */
        if (!cacheValid) {
            rwl.readLock().unlock(); //先把读锁释放掉
            rwl.writeLock().lock(); //上写锁

            if (!cacheValid) {
                System.out.println(Thread.currentThread().getName() + ": no cache!");
                //赋值
                data = new Random().nextInt(1000);
                //标记已经有缓存了
                cacheValid = true;
                System.out.println(Thread.currentThread().getName() + ": already cached!");
            }

            rwl.readLock().lock(); //再把读锁上上
            rwl.writeLock().unlock(); //把刚刚上的写锁释放掉
        }

        System.out.println(Thread.currentThread().getName() + " get data: " + data);
        rwl.readLock().unlock(); //释放读锁
    }
}
