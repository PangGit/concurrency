package com.concurrent.coll013;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;


public class UseQueue {

    public static void main(String[] args) throws Exception {


/*        {
            //高性能无阻塞无界队列：ConcurrentLinkedQueue
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
        }*/

        //阻塞队列
/*        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.add("c");
        arrayBlockingQueue.add("d");
        arrayBlockingQueue.add("e");
        arrayBlockingQueue.add("f");
        System.out.println(arrayBlockingQueue.offer("a", 3, TimeUnit.SECONDS));*/


/*        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.offer("a");
        linkedBlockingQueue.offer("b");
        linkedBlockingQueue.offer("c");
        linkedBlockingQueue.offer("d");
        linkedBlockingQueue.offer("e");
        linkedBlockingQueue.add("f");
        System.out.println(linkedBlockingQueue.size());

        for (Iterator iterator = linkedBlockingQueue.iterator(); iterator.hasNext(); ) {
            String string = (String) iterator.next();
            System.out.println(string);
        }

        List<String> list = new ArrayList<>();

        System.out.println(linkedBlockingQueue.drainTo(list, 3));
        System.out.println(list.size());

        for (String string : list) {
            System.out.println(string);
        }*/


        {
//        作为BlockingQueue中的一员，SynchronousQueue与其他BlockingQueue有着不同特性：
//        1.SynchronousQueue没有容量。与其他BlockingQueue不同，SynchronousQueue是一个不存储元素的BlockingQueue。每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
//        2.因为没有容量，所以对应 peek, contains, clear, isEmpty … 等方法其实是无效的。例如clear是不执行任何操作的，contains始终返回false,peek始终返回null。
//        3.SynchronousQueue分为公平和非公平，默认情况下采用非公平性访问策略，当然也可以通过构造函数来设置为公平性访问策略（为true即可）。
//        4.若使用 TransferQueue, 则队列中永远会存在一个 dummy node（这点后面详细阐述）。

/*            final SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
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
            t2.start();*/
        }

    }
}
