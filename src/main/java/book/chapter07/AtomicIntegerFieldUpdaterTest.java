package book.chapter07;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author tengfei.fangtf
 * @version $Id: AtomicIntegerFieldUpdaterTest.java, v 0.1 2015-8-1 上午12:04:51 tengfei.fangtf Exp $
 */
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

    public static void main(String[] args) {
        User conan = new User("conan", 10);
        System.out.println(a.getAndIncrement(conan));
        System.out.println(a.get(conan));
    }

    public static class User {
        private String name;
        public volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}
