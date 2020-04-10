package com.thread.lock0;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUseCase {

    private int i =0;

    private void test(){
        Lock lock = new ReentrantLock();
        lock.lock();
        try{
            ++i;
            System.out.println(i);
        }finally {
            lock.unlock();
        }
    }


}
