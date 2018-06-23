package com.concurrent.coll013_queue;

import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeTest {

    public static void main(String[] args) {

        LinkedBlockingDeque<String> dq = new LinkedBlockingDeque<>(11);

        dq.addFirst("1");
        dq.addFirst("2");
        dq.addFirst("3");
        dq.addFirst("4");
        dq.addFirst("5");

        dq.addLast("6");
        dq.addLast("7");
        dq.addLast("8");
        dq.addLast("9");
        dq.addLast("10");

        // Links node as first element, or returns false if full.
        dq.offerFirst("11");

        // returns first element, or null if empty.
        System.out.println("returns first element ：" + dq.peekFirst());

        // Removes and returns last element, or null if empty.
        System.out.println("removes and returns last element ：" + dq.pollLast());

        Object[] obj = dq.toArray();
        for (Object o : obj) {
            System.out.println(o);
        }

    }
}
