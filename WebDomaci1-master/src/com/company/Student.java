package com.company;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class Student implements Runnable {


    private String studentName;
    private String ispitivac;
    private Profesor profesor;
    private Asistent asistent;
    private long vremeDolaska;
    private long odbranaPocetak = -1;
    private long odbrana;
    private boolean zavrsio = false;
    private int ocena = 0;


    public Student(String studentName, Asistent asistent, Profesor profesor, long odbrana) {
        this.studentName = studentName;
        this.profesor = profesor;
        this.asistent = asistent;
        this.odbrana = odbrana;
    }


    @Override
    public void run() {
        vremeDolaska = System.currentTimeMillis();

        if (vremeDolaska + odbrana - Main.vremeInicijalizacije >= 5000)
            return;

        while (Main.flag.get() && !zavrsio) {
            Random random = new Random();
            int novi = random.nextInt(2);
            if (novi == 0) {
                if (Main.flag.get()) {
                    try {
                        profesor.getSemaphore().acquire();
                        profesor.getCyclicBarrier().await();
                        odbranaPocetak = System.currentTimeMillis();

                    } catch (InterruptedException e) {
                        return;

                    } catch (BrokenBarrierException e) {
                        continue;
                    }
                    try {
                        Thread.sleep(odbrana);

                    } catch (InterruptedException e) {
                        vremeDolaska -= Main.vremeInicijalizacije;
                        odbranaPocetak -= Main.vremeInicijalizacije;
                        ispitivac = profesor.getName();

                        System.out.println("\n");
                        System.out.println("Interrapted: " + studentName +
                                " | Arrival time: " + vremeDolaska + "ms" +
                                " | Prof: " + ispitivac +
                                " | Pocetak odbrane: " + odbranaPocetak + "ms" +
                                " | TTC: " + odbrana + "ms");
                        return;
                    }

                    profesor.getSemaphore().release();

                    ocena = profesor.scoreStudent();
                    zavrsio = true;
                    ispitivac = profesor.getName();

                }
            } else {
                ondaAsistent();
            }
            if (zavrsio) {
                Main.suma.addAndGet(ocena);
                Main.branili.incrementAndGet();
                vremeDolaska -= Main.vremeInicijalizacije;
                odbranaPocetak -= Main.vremeInicijalizacije;


                System.out.println("Thread: <" + studentName + ">" +
                        " < Arrival time: " + vremeDolaska + "ms>" +
                        " < Prof: " + ispitivac + ">" +
                        " < Pocetak odbrane: " + odbranaPocetak + "ms>" +
                        " < TTC: " + odbrana + "ms>" +
                        " < Score: " + ocena + ">");

            }
        }
    }

    private void ondaAsistent() {
        if (!zavrsio && Main.flag.get()) {
            try {
                asistent.getSemaphore().acquire();
                odbranaPocetak = System.currentTimeMillis();
                try {
                    Thread.sleep(odbrana);
                } catch (InterruptedException e) {
                    vremeDolaska -= Main.vremeInicijalizacije;
                    odbranaPocetak -= Main.vremeInicijalizacije;
                    ispitivac = asistent.getName();

                    System.out.println("\n");
                    System.out.println("Interrapted: " + studentName +
                            " | Arrival time: " + vremeDolaska + "ms" +
                            " | Prof: " + ispitivac +
                            " | Pocetak odbrane: " + odbranaPocetak + "ms" +
                            " | TTC: " + odbrana + "ms");
                    return;
                }

                ocena = asistent.scoreStudent();
                zavrsio = true;
                ispitivac = asistent.getName();
                asistent.getSemaphore().release();

            } catch (InterruptedException e) {
                return;

            }
        }
    }
}
