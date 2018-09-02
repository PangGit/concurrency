package com.concurrent.coll013_queue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 网名
 *
 * @author dd
 */
public class Netizen implements Delayed {

    private String name;
    //身份证  
    private String id;
    //截止时间  
    private long endTime;
    //定义时间工具类
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public Netizen(String name, String id, long endTime) {
        this.name = name;
        this.id = id;
        this.endTime = endTime;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    /**
     * 用来判断是否到了截止时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        //return unit.convert(endTime, TimeUnit.MILLISECONDS) - unit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        return endTime - System.currentTimeMillis();
    }

    /**
     * 相互批较排序用
     */
    @Override
    public int compareTo(Delayed delayed) {
        System.out.println("compare:"+this.toString());
        Netizen w = (Netizen) delayed;
        return this.getDelay(this.timeUnit) - w.getDelay(this.timeUnit) > 0 ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Netizen{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", endTime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(endTime)) +
                ", timeUnit=" + timeUnit +
                '}';
    }
}