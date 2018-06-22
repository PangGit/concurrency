package com.concurrent.coll013_queue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description :
 * @version : 1.0
 * @since JDK1.8
 * @see  <a href="https://www.jianshu.com/p/c0642afe03e0">ConcurrentHashMap1.8</>
 */
public class ConcurrentMapTest {

    public static void main(String[] args) {

        /**
         * Creates a new, empty map with the default initial table size (16).
         */
        ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<>();

        chm.put("k1", "v1");
        chm.put("k2", "v2");
        chm.put("k3", "v3");

        chm.putIfAbsent("k3", "v4");

        System.out.println(chm.get("k2"));
        System.out.println(chm.size());

        for (Map.Entry<String, Object> me : chm.entrySet()) {
            System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
        }


    }
}
