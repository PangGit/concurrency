package book.chapter02;


/**
 * synchronized实现同步的基础：Java中的每一个对象都可以作为锁。
 * <p>
 * 具体表现为以下3种形式。
 *  ·对于普通同步方法，锁是当前实例对象。
 *  ·对于静态同步方法，锁是当前类的Class对象。
 *  ·对于同步方法块，锁是Synchronized括号里配置的对象。
 */
public class SynchronizedTest {

    private final Object lock = new Object();

    public int add1(int i,int j){
        return i+j;
    }

    public synchronized int add2(int i,int j){
        return  i+j;
    }

    public static synchronized int add3(int i,int j){
        return  i+j;
    }


    public int add4(int i,int j){
        synchronized (lock){
            return  i+j;
        }
    }



}
