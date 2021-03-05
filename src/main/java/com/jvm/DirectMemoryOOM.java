package com.jvm;

import sun.misc.Unsafe;

import java.awt.image.DataBufferByte;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @author zzm
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {

        // new DirectMemoryOOM().method1();

        new DirectMemoryOOM().method2();
    }

    private void method1() throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

    private void method2() {
        List<DataBufferByte> list = new ArrayList<>();
        while (true) {
            list.add(new DataBufferByte(_1MB));
        }
    }
}
