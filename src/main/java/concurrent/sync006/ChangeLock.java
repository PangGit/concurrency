package concurrent.sync006;

/**
 * 锁对象的改变问题
 *
 * @author PC-20170417-666
 */
public class ChangeLock {

    private String lock = "lock";

    private void method() {
        synchronized (lock) {
            try {
                System.out.println("当前线程 : " + Thread.currentThread().getName() + "开始");
                /*锁对象改变，线程2与线程1 就没有先后的顺序了。*/
                lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程 : " + Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final ChangeLock changeLock = new ChangeLock();

        Thread t1 = new Thread(changeLock::method, "t1");
        Thread t2 = new Thread(changeLock::method, "t2");

        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
