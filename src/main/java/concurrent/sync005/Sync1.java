package concurrent.sync005;

/**
 * synchronized的重入
 */
public class Sync1 {

    private synchronized void method1() {
        System.out.println("method1..");
        method2();
    }

    private synchronized void method2() {
        System.out.println("method2..");
        method3();
    }

    private synchronized void method3() {
        System.out.println("method3..");
    }

    public static void main(String[] args) {
        final Sync1 sd = new Sync1();
        Thread t1 = new Thread(sd::method1);
        t1.start();
    }
}