package com.jvm;

/**
 * 栈溢出
 * VM Args：-Xss2M （这时候不妨设大些，请在32位系统下运行）
 *
 * @author Pangdb
 */
public class JavaVMStackOOM {

    private void dontStop() {
        long current = System.currentTimeMillis();
        // 执行占用
        while (System.currentTimeMillis() - 10 * 1000L < current) {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stackLeakByThread() {
        long current = System.currentTimeMillis();
        // 执行占用
        while (System.currentTimeMillis() - 30 * 1000L < current) {
            (new Thread(this::dontStop)).start();
        }
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
