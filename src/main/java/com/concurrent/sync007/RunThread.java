package com.concurrent.sync007;

public class RunThread extends Thread {

    /**
     * volatile : 使变量在多个线程间可见。不具备同步性。
     * 如果一个字段被声明成volatile，Java线程内存模型确保所有线程看到这个变量的值是一致的。
     * 1）将当前处理器缓存行的数据写回到系统内存。
     * 2）这个写回内存的操作会使在其他CPU里缓存了该内存地址的数据无效。
     */
    private volatile boolean isRunning = true;

    private void setRunning() {
        this.isRunning = false;
    }

    @Override
    public void run() {
        System.out.println("thread start..");
        int i = 0;
        while (isRunning) {
            System.out.println("run...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("thread end...");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread rt = new RunThread();
        rt.start();

        Thread.sleep(3000);
        rt.setRunning();
        System.out.println("set isRunning = false");
    }


}
