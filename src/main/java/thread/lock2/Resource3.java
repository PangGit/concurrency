package thread.lock2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dab
 * @version 1.0.0
 * @Description : 3.Lock对象锁
 * <p>
 * 除了使用synchronized外，还可以使用Lock对象来创建临界区。
 * <p>
 * Resource3.java的演示效果同Resource1.java；
 * <p>
 * Resource4.java的演示效果同Resource2.java。
 * @Date 2018/5/23 19:12
 */

public class Resource3 {

    private Lock lock = new ReentrantLock();

    private void f() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in f()");
        lock.lock();
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in f()");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private void g() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in g()");
        lock.lock();
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in g()");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private void h() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in h()");
        lock.lock();
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in h()");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Resource3 rs = new Resource3();

        new Thread(() -> rs.f()).start();

        new Thread(() -> rs.g()).start();

        rs.h();
    }
}


