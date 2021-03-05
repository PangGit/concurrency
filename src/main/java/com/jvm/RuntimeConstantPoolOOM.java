package com.jvm;

import java.util.HashSet;
import java.util.Set;

/**
 * 运行时常量池导致的内存溢出异常
 * <p>
 * jdk6
 * VM Args: -XX:PermSize=6M -XX:MaxPermSize=6M
 * <p>
 * OpenJDK 64-Bit Server VM warning: Ignoring option PermSize; support was removed in 8.0
 * OpenJDK 64-Bit Server VM warning: Ignoring option MaxPermSize; support was removed in 8.0
 * <p>
 * jdk7+
 * VM Args: -XX:MaxMeta-spaceSize
 *
 * @author Pangdb
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用 Set 保持 着 常量 池 引用， 避免 Full GC 回收 常量 池 行为
        Set<String> set = new HashSet<>();

        // 在 short 范围内 足以 让 6MB 的 PermSize 产生 OOM 了
        short i = 0;

        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}

