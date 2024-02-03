package concurrent.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile关键字不具备synchronized关键字的原子性（同步）
 */
public class VolatileNoAtomic extends Thread {

    //private static volatile int count;

    /* AtomicInteger 原子性操作 */
    private static AtomicInteger count = new AtomicInteger(0);

    /*  synchronized 同步方法 */
    //private synchronized static void addCount() {

    private static void addCount() {
        for (int i = 0; i < 1000; i++) {
            //count++;
            count.incrementAndGet();
        }
        /* 这里jvm会另开一线程打印输出，较耗资源。*/
        System.out.println(count);
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {

        VolatileNoAtomic[] arr = new VolatileNoAtomic[100];
        for (int i = 0; i < 10; i++) {
            arr[i] = new VolatileNoAtomic();
        }

        for (int i = 0; i < 10; i++) {
            arr[i].start();
        }
    }
}