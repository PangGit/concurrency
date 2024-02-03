package concurrent.sync006;

/**
 * 使用synchronized代码块加锁,比较灵活
 */
public class ObjectLock {

    private void method1() {
        synchronized (this) {    //对象锁
            try {
                System.out.println("do method1..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method1 end ..");
        }
    }

    private void method2() {
        synchronized (ObjectLock.class) { //类锁
            try {
                System.out.println("do method2..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method2 end ..");
        }
    }

    private final Object lock = new Object();

    private void method3() {
        synchronized (lock) { //任何对象锁
            try {
                System.out.println("do method3..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method3 end ..");
        }
    }


    public static void main(String[] args) {
        final ObjectLock objLock = new ObjectLock();

        Thread t1 = new Thread(objLock::method1);
        Thread t2 = new Thread(objLock::method2);
        Thread t3 = new Thread(objLock::method3);
        t1.start();
        t2.start();
        t3.start();
    }

}
