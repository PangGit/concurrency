package com.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 *
 * @author pang
 * @since 2023/4/6 下午 10:25
 */
public class ThreadPoolUtil {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final ThreadFactory namedThreadFactory
            = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

    public static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            CPU_COUNT,
            CPU_COUNT * 2,
            3,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(1024),
            namedThreadFactory,
            new ThreadPoolExecutor.CallerRunsPolicy());

}
