package com.concurrent.coll013;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UseConcurrentMap {

    public static void main(String[] args) {

        /**
         * Creates a new, empty map with the default initial table size (16).
         */
        ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<>();

        chm.put("k1", "v1");
        chm.put("k2", "v2");
        chm.put("k3", "v3");

        chm.putIfAbsent("k4", "vvvv");
        //System.out.println(chm.get("k2"));
        //System.out.println(chm.size());

        for (Map.Entry<String, Object> me : chm.entrySet()) {
            System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
        }


    }
}
