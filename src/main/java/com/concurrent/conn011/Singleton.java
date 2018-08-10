package com.concurrent.conn011;

/**
 * @author PC-20170417-666
 */
public class Singleton {

    /**
     * 使用静态内部类，初始化示例。线程安全。
     */
    private static class InnerSingleton {
        private static Singleton single = new Singleton();

        static {
            System.out.println("静态内部类 静态代码块");
        }
    }

    public static Singleton getInstance() {
        return InnerSingleton.single;
    }

    private Singleton() {
        // 判断单例对象是否已经存在，用于控制非法单例类的构造函数。
        if (InnerSingleton.single != null) {
            try {
                throw new IllegalAccessException("非法反射构造函数");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> System.out.println(Singleton.getInstance().hashCode()), "t1");
        Thread t2 = new Thread(() -> System.out.println(Singleton.getInstance().hashCode()), "t2");
        Thread t3 = new Thread(() -> System.out.println(Singleton.getInstance().hashCode()), "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
