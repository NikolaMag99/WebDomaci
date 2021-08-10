package com.company;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Profesor implements Runnable {

    private CountDownLatch countDownLatch;
    private Semaphore semaphore;
    private CyclicBarrier cyclicBarrier;
    private String name;


    public Profesor(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        // ogranicavamo broj niti koji pristupaju resursu na 2
        semaphore = new Semaphore(2, true);
        cyclicBarrier = new CyclicBarrier(2);
        name = "Profesor tred";

    }


    public int scoreStudent() {
        return (new Random()).nextInt(11);
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        System.out.println("Profesor inicijalizovan.");
        countDownLatch.countDown();
        Main.printQueue.add("Profesor tred ugasen.");

    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public String getName() {
        return name;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

}
