package com.concurrent.coll012;

import java.util.*;

/**
 * 同步类容器：线程安全。但在某些场景下可能需要加锁来保护复合操作（迭代、跳转、条件运算）。 例如：Vector，HashTable
 * <p>
 * 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 *
 * @author PC-20170417-666
 */
public class Tickets {

    public static void main(String[] args) {

        //初始化火车票池并添加火车票:避免线程同步可采用Vector替代ArrayList  HashTable替代HashMap

        final Vector<String> tickets = new Vector<>();

        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

        for (int i = 1; i <= 100; i++) {
            tickets.add("火车票" + i);
        }

        /* Exception in thread "main" java.util.ConcurrentModificationException */
	/*	for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
            System.out.println(string);
			tickets.remove(20);
		}*/

        tickets.forEach(System.out::println);


/*        do {
            for (int i = 0; i < tickets.size(); i++) {
                tickets.remove(i);
            }
            System.out.println("火车票 余数：" + tickets.size());
        } while (tickets.size() > 0);*/


        for (int i = 1; i <= 10; i++) {
            new Thread("线程" + i) {
                @Override
                public void run() {
                    while (true) {
                        if (tickets.isEmpty()) {
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                    }
                }
            }.start();
        }
    }
}
