package com.thread.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dab
 * @version 1.0.0
 * @Description : 线程工厂，提供创建新线程的功能。
 * @Date 2018/5/23 16:32
 */
public class MyThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public MyThreadFactory() {
        SecurityManager var1 = System.getSecurityManager();
        this.group = var1 != null ? var1.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = "自定义 pool-" + poolNumber.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable var1) {
        Thread var2 = new Thread(this.group, var1, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
        if (var2.isDaemon()) {
            var2.setDaemon(false);
        }
        if (var2.getPriority() != 5) {
            var2.setPriority(5);
        }
        return var2;
    }
}
