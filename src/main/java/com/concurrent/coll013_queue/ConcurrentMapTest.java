package com.concurrent.coll013_queue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @version : 1.0
 * @Description :
 * @see <a href="https://www.jianshu.com/p/c0642afe03e0">ConcurrentHashMap1.8</>
 * @since JDK1.8
 */
public class ConcurrentMapTest {

    public static void main(String[] args) {

        {
            /*JDK1.7  采用 Segment分段锁机制，实现并发的更新操作，底层采用数组+链表的存储结构。*/
            /*JDK1.8  抛弃了 Segment分段锁机制，利用CAS+Synchronized来保证并发更新的安全，底层采用数组+链表+红黑树的存储结构。*/

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

        {
            // 支出并发排序
            ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();


        }


    }
}
