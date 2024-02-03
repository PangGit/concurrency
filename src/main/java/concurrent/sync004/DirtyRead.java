package concurrent.sync004;

/**
 * 脏读，业务整体需要使用完整的synchronized，保持业务的原子性。
 * <p>
 * ACID，指数据库事务正确执行的四个基本要素的缩写。包含：原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation）、持久性（Durability）。
 * 一个支持事务（Transaction）的数据库，必须要具有这四种特性，否则在事务过程（Transaction processing）当中无法保证数据的正确性，交易过程极可能达不到交易方的要求。
 */
public class DirtyRead {

    private String username = "a123";
    private String password = "123";

    private synchronized void setValue(String username, String password) {
        this.username = username;
        try {
            /*某些业务很耗时间*/
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("setValue 结果：username = " + username + " , password = " + password);
    }

    /* 解决方法:在get|set方法上都synchronized，在共享资源的一致性的操作，都要加锁。*/
    public void getValue() {
        System.out.println("getValue 结果：username = " + this.username + " , password = " + this.password);
    }

    public static void main(String[] args) throws Exception {
        final DirtyRead dr = new DirtyRead();

        Thread t1 = new Thread(() -> dr.setValue("b456", "456"));
        t1.start();

        Thread.sleep(1000);
        dr.getValue();
    }
}