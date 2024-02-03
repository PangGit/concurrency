package thread.lock2;

import java.util.concurrent.TimeUnit;

/**
 * @author dab
 * @version 1.0.0
 * @Description : 2.2 同步到多个对象锁
 * <p>
 * Resource1.java演示了三个线程（包括main线程）试图进入某个类的三个不同的方法的同步块中，
 * <p>
 * 这些同步块处在不同的方法中，并且是同步到三个不同的对象（synchronized (this)，synchronized(syncObject1)，synchronized (syncObject2)），
 * <p>
 * 所以对它们的方法中的临界资源访问是独立的。
 * @Date 2018/5/23 19:08
 */
public class Resource2 {

    private final Object syncObject1 = new Object();
    private final Object syncObject2 = new Object();

    private void f() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in f()");
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in f()");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void g() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in g()");
        synchronized (syncObject1) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in g()");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void h() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in h()");
        synchronized (syncObject2) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in h()");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final Resource2 rs = new Resource2();

        new Thread(() -> rs.f()).start();

        new Thread(() -> rs.g()).start();

        rs.h();
    }
}
