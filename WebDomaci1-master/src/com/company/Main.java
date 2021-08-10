package com.company;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    public static long vremeInicijalizacije;
    public static AtomicInteger suma = new AtomicInteger();
    public static AtomicInteger branili = new AtomicInteger();
    public static AtomicBoolean flag = new AtomicBoolean(true);

    public static ConcurrentLinkedQueue<String> printQueue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws InterruptedException {


        System.out.println("Unesite broj studenata: ");
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.close();


        //inicijalizujemo ispitivace, koristimo CountDownLatch da bismo bili sigurni da su oba inicijalizovana pre nego sto program pocne
        CountDownLatch ispitivaciIni = new CountDownLatch(2);
        Profesor profesor = new Profesor(ispitivaciIni);
        Asistent asistent = new Asistent(ispitivaciIni);


        // pravimo ExecutorService objekat koji ima pul od 2 treda
        //ExecutorService je interfejs koji nam dozvoljava da asinhrono izvrsavamo zadatke na tredovima(nitima).
        ExecutorService ispitivaciService = Executors.newFixedThreadPool(2);
        ispitivaciService.execute(profesor);
        ispitivaciService.execute(asistent);


//        Thread profeso = new Thread(profesor);
//        Thread asistento = new Thread(asistent);
//
//        profeso.start();
//        asistento.start();

        //cekamo da se svi ispitivaci inicijalizuju
        ispitivaciIni.await();
        System.out.println("Odbrana moze da pocne!\n");

        // Inicijalizacija pocetka odbrane
        vremeInicijalizacije = System.currentTimeMillis();

        // Inicijalizacija studenta
        ScheduledExecutorService executorServiceStundeti = Executors.newScheduledThreadPool(N);


        for (int i = 0; i < N; i++) {
            long odbrana = (new Random()).nextInt(500) + 500;
            long zadrzavanje = (new Random()).nextInt(1000);
            String studentName = "Student " + (i + 1);
            Student student = new Student(studentName, asistent, profesor, odbrana);
            executorServiceStundeti.schedule(student, zadrzavanje, TimeUnit.MILLISECONDS);
        }

        //odbrana 5 sekundi
        // Thread.sleep(5000);
        Tajmer tajmer = new Tajmer();
        Thread pocetak = new Thread(tajmer);
        pocetak.start();
        pocetak.join();

        //gasimo sve nakon zavrsetka odbrane
        flag.set(false);
        Main.printQueue.add("");
        executorServiceStundeti.shutdownNow();
        System.out.println("\n");
        ispitivaciService.shutdownNow();

        DecimalFormat decimal = new DecimalFormat("0.00");
        Main.printQueue.add("\nBranilo stundenata: " + branili.intValue());
        Main.printQueue.add("Ukupan skor: " + suma.intValue());
        Main.printQueue.add("Procesan skor: " + decimal.format(suma.doubleValue() / branili.doubleValue()));


        for (String s : printQueue) {
            System.out.println(s);
        }

    }
}
