package com.concurrent.coll013_queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级阻塞队列 PriorityBlockingQueue
 * <p>
 * 该实现类需要自己实现一个继承了 Comparator 接口的类， 在插入资源时会按照自定义的排序规则来对资源数组进行排序。
 * <p>
 * 其中值大的排在数组后面 ，取值时从数组头开始取.
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) throws Exception {

        PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<>();

        Task t1 = new Task();
        t1.setId(3);
        t1.setName("id=3");

        Task t2 = new Task();
        t2.setId(4);
        t2.setName("id=4");

        Task t3 = new Task();
        t3.setId(1);
        t3.setName("id=1");

        //return this.id > task.id ? 1 : 0;
        q.add(t1);
        q.add(t2);
        q.add(t3);

        // 1 3 4
        System.out.println("--1--容器：" + q);

        // 在获取时，对容器整体排序。
        System.out.println(q.take());
        System.out.println("--2--容器：" + q);

        System.out.println(q.take());
        System.out.println(q.take());
        System.out.println("--3--容器：" + q);


    }
}
