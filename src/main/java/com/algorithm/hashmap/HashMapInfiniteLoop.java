package com.algorithm.hashmap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author dab
 * @version 1.0.0
 * @Description :
 * @Date 2018/5/30 17:47
 */
public class HashMapInfiniteLoop {

    private static HashMap<Integer, String> map = new HashMap<>(2, 0.75f);

    private static ConcurrentMap<Integer, String> cMap = new ConcurrentHashMap<>(4);

    public static void main(String[] args) {
        cMap.put(5, "C");

        new Thread("Thread1") {
            @Override
            public void run() {
                cMap.put(7, "B");
                System.out.println(cMap);
            }
        }.start();

        new Thread("Thread2") {
            @Override
            public void run() {
                cMap.put(3, "A");
                System.out.println(cMap);
            }
        }.start();




    }
}
