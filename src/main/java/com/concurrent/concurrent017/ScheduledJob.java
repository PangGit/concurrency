package com.concurrent.concurrent017;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.*;

class Temp extends Thread {

    @Override
    public void run() {
        System.out.println("run");
    }
}

public class ScheduledJob {

    public static void main(String args[]) throws Exception {

        Temp command = new Temp();
        ScheduledExecutorService scheduler = newScheduledThreadPool(1);

        ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(command, 5, 1, TimeUnit.SECONDS);

    }
}