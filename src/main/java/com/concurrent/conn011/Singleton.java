package com.concurrent.conn011;

/**
 * @author PC-20170417-666
 */
public class Singleton {

	/**  使用静态内部类，初始化示例。 */
	private static class InnerSingleton {
		private static Singleton single = new Singleton();
	}
	
	private static Singleton getInstance(){
		return InnerSingleton.single;
	}

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> System.out.println(getInstance().hashCode()),"t1");
		Thread t2 = new Thread(() -> System.out.println(getInstance().hashCode()),"t2");
		Thread t3 = new Thread(() -> System.out.println(getInstance().hashCode()),"t3");

		t1.start();
		t2.start();
		t3.start();
	}
}
