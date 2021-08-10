package server;

import model.Status;

import java.util.Random;

public class Runda implements Runnable {

    //Ako si seo izvlacis ili bidujes i izvlaci se koji ce biti najkraci stapic u rundi
    @Override
    public void run() {
        if (Sto.koIzvlaci < 6) {
            for (ServerThread s : Sto.serverThreads) {
                if (s.getBrojMesta() > -1) {
                    s.getResponse().setStatus(Status.BID);
                    if (s.getBrojMesta() == Sto.koIzvlaci) {
                        s.getResponse().setStatus(Status.DRAW);
                    }
                }
            }
            Sto.najkraciStapic = (new Random()).nextInt(6) + 1;
            System.out.println("Najkraci stapic u ovoj rundi je: " + Sto.najkraciStapic);
        }
    }
}
