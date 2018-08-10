package com.concurrent.conn009;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;

/**
 * 著名的Java并发编程大师 Doug lea在JDK 7的并发包里新增一个队列集合类LinkedTransferQueue，
 * 它在使用volatile变量时，用一种追加字节的方式来优化队列出队和入队的性能。
 * <p>
 * 追加字节能优化性能？
 * <p>
 * 这种方式看起来很神奇，但如果深入理解处理器架构就能理解其中的奥秘。
 * 让我们先来看看LinkedTransferQueue这个类，它使用一个内部类类型来定义队列的头节点（head）和尾节点（tail），
 * 而这个内部类PaddedAtomicReference相对于父类 AtomicReference只做了一件事情，就是将共享变量追加到64字节。
 * 我们可以来计算下，一个对象的引用占4个字节，它追加了15个变量（共占60个字节），再加上父类的value变量，一共64个字节。
 * <p>
 * 为什么追加64字节能够提高并发编程的效率呢？
 * <p>
 * 因为对于英特尔酷睿i7、酷睿、Atom和NetBurst，以及Core Solo和Pentium M处理器的L1、L2或L3缓存的高速缓存行是64个字节宽，
 * 不支持部分填充缓存行，这意味着，如果队列的头节点和尾节点都不足64字节的话，处理器会将它们都读到同一个高速缓存行中，
 * 在多处理器下每个处理器都会缓存同样的头、尾节点，当一个处理器试图修改头节点时，会将整个缓存行锁定，那么在缓存一致性机制的作用下，
 * 会导致其他处理器不能访问自己高速缓存中的尾节点，而队列的入队和出队操作则需要不停修改头节点和尾节点，
 * 所以在多处理器的情况下将会严重影响到队列的入队和出队效率。Doug lea使用追加到64字节的方式来填满高速缓冲区的缓存行，
 * 避免头节点和尾节点加载到同一个缓存行，使头、尾节点在修改时不会互相锁定。
 * <p>
 * 在两种场景下不应该使用这种方式。
 * 1.缓存行非64字节宽的处理器。
 * 2.共享变量不会被频繁地写。
 */
public class LinkedTransferQueueTest {

    private static volatile LinkedTransferQueue<String> ltq = new LinkedTransferQueue<>();

    public static void main(String[] args) {

        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < Runtime.getRuntime().availableProcessors() + 1; i++) {
            int finalI = i;
            list.add(new Thread(() -> {
                while (ltq.size() < 1_0000) {
                    boolean b = ltq.add("thread" + finalI);
                }
            }));
        }

        Long time = System.currentTimeMillis();

        for (Thread t : list) {
            t.start();
        }

        // 等待所有线程执行完成
        for (Thread t : list) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Long time2 = System.currentTimeMillis();
        System.out.println("time : " + (time2 - time));
        System.out.println("ltq.size : " + ltq.size());

        HashMap<String, Integer> hashMap = new HashMap<>();
        for (Object aLtq : ltq) {
            String obj = (String) aLtq;
            if (hashMap.get(obj) == null) {
                hashMap.put(obj, 1);
            } else {
                int i = hashMap.get(obj);
                hashMap.put(obj, i + 1);
            }
        }

        Set<String> set = hashMap.keySet();
        for (String key : set) {
            System.out.println(key + ":" + hashMap.get(key));
        }


    }
}
