package com.concurrent.coll013_queue;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 高性能无阻塞无界队列
 * An unbounded thread-safe {@linkplain Queue queue} based on linked nodes.
 * This queue orders elements FIFO (first-in-first-out).
 */
public class ConcurrentLinkedDequeTest {

    public static void main(String[] args) {

        ConcurrentLinkedQueue<Object> clq = new ConcurrentLinkedQueue<>(new ArrayList<>(1));

        clq.add("a");
        clq.offer("c");

        System.out.println(clq.peek());
        System.out.println(clq.poll());

        System.out.println("------------------");
        for (Object o : clq) {
            System.out.println(o.toString());
        }

        System.out.println("------------------");

        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue.offer("a"); //将指定元素插入此队列的尾部。
        concurrentLinkedQueue.offer("b");
        concurrentLinkedQueue.offer("c");
        concurrentLinkedQueue.offer("d");
        concurrentLinkedQueue.add("e");
        // ConcurrentLinkedQueue中的add() 和 offer() 完全一样，都是往队列尾部添加元素

        System.out.println(concurrentLinkedQueue.poll());    //a  获取并移除此队列的头，如果此队列为空，则返回 null。
        System.out.println(concurrentLinkedQueue.size());    //4

        System.out.println(concurrentLinkedQueue.peek());    //b 获取但不移除此队列的头；如果此队列为空，则返回 null
        System.out.println(concurrentLinkedQueue.size());    //4

    }
}
