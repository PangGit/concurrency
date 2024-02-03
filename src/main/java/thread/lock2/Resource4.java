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
 * @Date 2018/5/23 19:15
 */
public class Resource4 {

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    private Lock lock3 = new ReentrantLock();

    private void f() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in f()");
        lock1.lock();
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in f()");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock1.unlock();
        }
    }

    private void g() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in g()");
        lock2.lock();
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in g()");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock2.unlock();
        }
    }

    private void h() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in h()");
        lock3.lock();
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in h()");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock3.unlock();
        }
    }

    public static void main(String[] args) {
        final Resource4 rs = new Resource4();

        new Thread(() -> rs.f()).start();

        new Thread(() -> rs.g()).start();

        rs.h();
    }
}
