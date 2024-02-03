package thread.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/w605283073/article/details/89930154
 * https://blog.csdn.net/w605283073/article/details/89930497
 *
 * @author Pangdb
 */
public class ThreadPoolExecutorTest2 {

    static class MyThread implements Runnable {
        String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程:" + Thread.currentThread().getName() + " 执行:" + name + "  run");
        }
    }

    public static void main(String[] args) {
        int size = 6;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(2),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < size; i++) {

            System.out.println("添加第" + i + "个任务");
            executor.execute(new MyThread("线程" + i));

            for (Runnable runnable : executor.getQueue()) {
                MyThread thread = (MyThread) runnable;
                System.out.println("列表：" + thread.name);
            }
        }

        executor.shutdown();

    }


}
