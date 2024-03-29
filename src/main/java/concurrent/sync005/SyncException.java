package concurrent.sync005;

/**
 * synchronized异常
 */
public class SyncException {

    private int i = 0;

    private synchronized void operation() {
        do {
            try {
                i++;
                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName() + " , i = " + i);
                if (i == 20) {
                    //Integer.parseInt("a");
                    throw new RuntimeException();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    public static void main(String[] args) {
        final SyncException se = new SyncException();
        Thread t1 = new Thread(se::operation, "t1");
        t1.start();
    }
}
