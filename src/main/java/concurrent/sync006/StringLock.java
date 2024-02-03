package concurrent.sync006;

/**
 * synchronized代码块对字符串的锁，注意String常量池的缓存功能
 *
 * @author PC-20170417-666
 */
public class StringLock {

    private void method() {
        //new String("字符串常量") // 不是同一个锁。
        synchronized ("字符串常量") {
            do {
                System.out.println("当前线程 : " + Thread.currentThread().getName() + "开始");
                //Thread.sleep(1000);
                System.out.println("当前线程 : " + Thread.currentThread().getName() + "结束");
            } while (true);
        }
    }

    public static void main(String[] args) {
        final StringLock stringLock = new StringLock();

        Thread t1 = new Thread(stringLock::method, "t1");
        Thread t2 = new Thread(stringLock::method, "t2");

        t1.start();
        t2.start();
    }
}
