package book.chapter01;

/**
 * 并发和单线程执行测试
 *
 * @author tengfei.fangtf
 * @version $Id: ConcurrencyTest.java, v 0.1 2014-7-18 下午10:03:31 tengfei.fangtf Exp $
 */
public class ConcurrencyTest {

    /**
     * 执行次数
     */
    private static final long count = 10_0000_0000l;

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long a = 0L;
                for (int i = 0; i < count; i++) {
                    a += 1;
                }
                System.out.println(a);
            }
        });
        thread.start();
        long b = 0L;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        long a = 0L;
        for (long i = 0; i < count; i++) {
            a += 1;
        }
        long b = 0L;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }


    public static void main(String[] args) throws InterruptedException {
        //并发计算
        concurrency();
        //单线程计算
        serial();

        System.out.println("----当前设备的CPU个数----" + Runtime.getRuntime().availableProcessors());
    }

}
