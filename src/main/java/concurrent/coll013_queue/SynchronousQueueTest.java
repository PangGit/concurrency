package concurrent.coll013_queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author PC-20170417-666
 */
public class SynchronousQueueTest {

    public static void main(String[] args) throws Exception {

        // 作为BlockingQueue中的一员，SynchronousQueue与其他BlockingQueue有着不同特性：

        // 1.SynchronousQueue没有容量。与其他BlockingQueue不同，SynchronousQueue是一个不存储元素的BlockingQueue。
        //      每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。

        // 2.因为没有容量，所以对应 peek, contains, clear, isEmpty … 等方法其实是无效的。
        //      例如clear是不执行任何操作的，contains始终返回false,peek始终返回null。

        // 3.SynchronousQueue分为公平和非公平，默认情况下采用非公平性访问策略，当然也可以通过构造函数来设置为公平性访问策略（为true即可）。

        // 4.若使用 TransferQueue, 则队列中永远会存在一个 dummy node（这点后面详细阐述）。

        final SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        Thread t1 = new Thread(() -> {
            try {
                // Retrieves and removes the head of this queue, waiting if necessary for another thread to insert it.
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        TimeUnit.SECONDS.sleep(2);

        Thread t2 = new Thread(() -> synchronousQueue.add("abc"));
        t2.start();
    }


}
