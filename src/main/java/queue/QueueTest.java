package queue;

import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * 队列
 *
 * @author Pangdb
 */
public class QueueTest {

    public static void main(String[] args) {
        {
            // 普通队列（Queue）是指实现了先进先出的基本队列
            // 例如 ArrayBlockingQueue 和 LinkedBlockingQueue
            ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(6);
            queue.offer("Hello");
            queue.offer("Java");
            queue.offer("中文社群");
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }

            // offer()：添加元素，如果队列已满直接返回 false，队列未满则直接插入并返回 true；
            // poll()：删除并返回队头元素，当队列为空返回 null；
            // add()：添加元素，此方法是对 offer 方法的简单封装，如果队列已满，抛出 IllegalStateException 异常；
            // remove()：直接删除队头元素；
            // put()：添加元素，如果队列已经满，则会阻塞等待插入；
            // take()：删除并返回队头元素，当队列为空，则会阻塞等待；
            // peek()：查询队头元素，但不会进行删除；
            // element()：对 peek 方法进行简单封装，如果队头元素存在则取出并不删除，如果不存在抛出 NoSuchElementException 异常。

            // 注意：一般情况下 offer() 和 poll() 方法配合使用，put() 和 take() 阻塞方法配合使用，add() 和 remove() 方法会配合使用，
            // 程序中常用的是 offer() 和 poll() 方法，因此这两个方法比较友好，不会报错。
        }

        {
            // 双端队列（Deque）是指队列的头部和尾部都可以同时入队和出队的数据结构
            LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(3);
            // 插入首个元素
            deque.offer("offer");
            // 队头插入元素
            deque.offerFirst("offerFirst");
            // 队尾插入元素
            deque.offerLast("offerLast");
            while (!deque.isEmpty()) {
                // 从头遍历打印
                System.out.println(deque.poll());
            }
        }

        {
            // 优先队列（PriorityQueue）是一种特殊的队列，它并不是先进先出的，而是优先级高的元素先出队。
            // 根据二叉堆实现的，二叉堆分为两种类型：最大堆、最小堆。
            // 在最大堆中，任意一个父节点的值都大于等于它左右子节点的值。
            PriorityQueue<Viper> queue = new PriorityQueue<>(10, new Comparator<Viper>() {
                @Override
                public int compare(Viper v1, Viper v2) {
                    // 设置优先级规则（倒序，等级越高权限越大）
                    return v2.getLevel() - v1.getLevel();
                }
            });
            // 构建实体类
            Viper v1 = new Viper(1, "Java", 1);
            Viper v2 = new Viper(2, "MySQL", 5);
            Viper v3 = new Viper(3, "Redis", 3);
            // 入列
            queue.offer(v1);
            queue.offer(v2);
            queue.offer(v3);
            while (!queue.isEmpty()) {
                // 遍历名称
                Viper item = queue.poll();
                System.out.println("Name：" + item.getName() + " Level：" + item.getLevel());
            }
        }

        {
            // SynchronousQueue
            // 内部没有容器，每次进行 put() 数据后（添加数据），必须等待另一个线程拿走数据后才可以再次添加数据
            SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

            // 入队
            new Thread(() -> {
                for (int i = 0; i < 3; i++) {
                    try {
                        System.out.println(new Date() + "，元素入队");
                        synchronousQueue.put("Data " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            // 出队
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(new Date() + "，元素出队：" + synchronousQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new SynchronousQueue<>());


        }
    }

}


/**
 * 自定义的实体类
 */
class Viper {
    private int id;
    private String name;
    private int level;

    public Viper(int id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
