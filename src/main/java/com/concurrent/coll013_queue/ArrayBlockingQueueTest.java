package com.concurrent.coll013_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

// 阻塞队列 : 定长数组
public class ArrayBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(5);

        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");

        arrayBlockingQueue.add("c");
        arrayBlockingQueue.add("d");
        arrayBlockingQueue.add("e");

        System.out.println("获取并移除此队列的头："+arrayBlockingQueue.poll());

        /*  Inserts the specified element at the tail of this queue,
         *   waiting up to the specified wait time for space to become available if the queue is full.
        */
        System.out.println(arrayBlockingQueue.offer("a", 1, TimeUnit.SECONDS));
    }
}
