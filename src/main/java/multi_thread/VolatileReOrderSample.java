package multi_thread;

/**
 * <a href="https://juejin.cn/post/6901283327160877063">指令重排序</a>
 * <p>
 * Java中的指令重排序有两次，<p>
 * 第一次发生在将字节码编译成机器码的阶段，<p>
 * 第二次发生在CPU执行的时候，也会适当对指令进行重排。
 */
public class VolatileReOrderSample {

    //定义四个静态变量
    private static int x = 0, y = 0;
    // 修饰加 volatile ： 禁止重排序
    // 第一个操作是 volatile 写操作，
    // 第二个操作是 volatile 读操作。
    private static volatile int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            //开两个线程，第一个线程执行a=1;x=b;第二个线程执行b=1;y=a
            Thread thread1 = new Thread(() -> {
                //线程1会比线程2先执行，因此用nanoTime让线程1等待线程2 0.01毫秒
                shortWait();
                a = 1;
                x = b;
            });
            Thread thread2 = new Thread(() -> {
                b = 1;
                y = a;
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            //等两个线程都执行完毕后拼接结果
            String result = "第" + i + "次执行x=" + x + "y=" + y;
            //如果x=0且y=0，则跳出循环
            if (x == 0 && y == 0) {
                // 因为指令被重排序了，x=b先于a=1执行，y=a先于b=1执行。
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }

    private static void shortWait() {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + (long) 10000 >= end); // 等待10000纳秒
    }
}
