package concurrent.coll013_queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) {

        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

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
        }

    }
}
