package concurrent.conn011;

/**
 * @author PC-20170417-666
 * <p>
 * 双重验证
 */
public class DoubleSingleton {

    private static DoubleSingleton ds;

    private static DoubleSingleton getDs() {
        if (ds == null) {
            try {
                //模拟初始化对象的准备时间...
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DoubleSingleton.class) {
                if (ds == null) {
                    ds = new DoubleSingleton();
                }
            }
        }
        return ds;
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> System.out.println(DoubleSingleton.getDs().hashCode()), "t1");

        Thread t2 = new Thread(() -> System.out.println(DoubleSingleton.getDs().hashCode()), "t2");

        Thread t3 = new Thread(() -> System.out.println( getDs().hashCode()), "t3");

        t1.start();
        t2.start();
        t3.start();
    }

}
