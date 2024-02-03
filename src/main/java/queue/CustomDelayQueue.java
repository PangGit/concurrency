package queue;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


/**
 * 延迟队列（DelayQueue）
 * 基于优先队列 PriorityQueue 实现的，它可以看作是一种以时间为度量单位的优先的队列，当入队的元素到达指定的延迟时间之后方可出队。
 *
 * @author Pangdb
 */
public class CustomDelayQueue {

    /**
     * 延迟消息队列
     */
    private static final DelayQueue<MyDelay> DELAY_QUEUE = new DelayQueue<>();

    public static void main(String[] args) throws InterruptedException {
        // 调用生产者
        producer();
        // 调用消费者
        consumer();
    }

    /**
     * 生产者
     */
    public static void producer() {
        // 添加消息
        DELAY_QUEUE.put(new MyDelay(1000, "消息1"));
        DELAY_QUEUE.put(new MyDelay(3000, "消息2"));
        DELAY_QUEUE.put(new MyDelay(2000, "消息3"));
    }

    /**
     * 消费者
     */
    public static void consumer() throws InterruptedException {
        System.out.println("开始执行时间：" + DateFormat.getDateTimeInstance().format(new Date()));
        while (!DELAY_QUEUE.isEmpty()) {
            System.out.println(DELAY_QUEUE.take());
        }
        System.out.println("结束执行时间：" + DateFormat.getDateTimeInstance().format(new Date()));
    }

    static class MyDelay implements Delayed {

        // 延迟截止时间（单位：毫秒）
        long delayTime = System.currentTimeMillis();

        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         * 初始化
         *
         * @param delayTime 设置延迟执行时间
         * @param msg       执行的消息
         */
        public MyDelay(long delayTime, String msg) {
            this.delayTime = (this.delayTime + delayTime);
            this.msg = msg;
        }

        // 获取剩余时间
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        // 队列里元素的排序依据
        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return this.msg;
        }
    }
}
