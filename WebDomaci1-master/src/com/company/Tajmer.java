package com.company;

public class Tajmer implements Runnable{

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }
}
