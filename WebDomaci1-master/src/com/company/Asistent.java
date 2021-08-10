package com.company;

import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Asistent implements Runnable {

    private String name;
    private CountDownLatch countDownLatch;
    private Semaphore semaphore;


    public Asistent(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        semaphore = new Semaphore(1);
        name = "Asistent tred";
    }

    public int scoreStudent() {
        return (new Random()).nextInt(11);
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        System.out.println("Asistent inicijalizovan");
        countDownLatch.countDown();
        Main.printQueue.add("Asistent tred ugrasen");

    }

    public String getName() {
        return name;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
