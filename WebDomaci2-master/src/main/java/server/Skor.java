package server;

import model.Status;

public class Skor implements Runnable {

    @Override
    public void run() {

        for (ServerThread s : Sto.serverThreads) {
            if (s.getBrojMesta() > -1) {
                if (s.getBrojMesta() != Sto.koIzvlaci) {
                    if (s.getGuess() == Sto.koNajkraciIzvukao) s.setPoeni(s.getPoeni() + 1);
                } else {
                    if (Sto.koNajkraciIzvukao) {
                        //ispadam iz igre
                        s.setFlag(false);
                        s.setPoeni(-1);
                        //oslobadjam njegovo mesto
                        for (int i = 0; i < Sto.mestaZaStolom; i++) {
                            if (Sto.igraci[i].equals(s.getIgracID())) {
                                Sto.igraci[i] = null;
                                break;
                            }
                        }
                        System.out.println("Igrac je izvukao najkraci stapic. Oslobadjamo semafor.\n");
                        break;
                    }
                }
            }
        }


        Sto.currentIteration++;
        //sldeci igrac
        Sto.koIzvlaci++;
        System.out.println("");

        // kad ispadne daj mu 0 poena
        if (Sto.koNajkraciIzvukao) {
            for (ServerThread s : Sto.serverThreads) {
                if (s.getBrojMesta() > -1) {
                    s.setPoeni(0);
                }
            }
            Sto.koIzvlaci = 0;
        }

        //kraj igre
        if (Sto.koIzvlaci >= 6 || Sto.currentIteration >= Sto.maxBrojRundi) {
            System.out.println("\n---------------------------------------- GAME OVER ----------------------------------------\n\n");

            if (Sto.currentIteration == Sto.maxBrojRundi) {
                System.out.println("Program je zavrsen jer je odigran maksimalan broj rundi (" + Sto.currentIteration + ").");
            }
            for (ServerThread s : Sto.serverThreads) {
                s.setFlag(false);
                s.getResponse().setStatus(Status.FINISHED);
            }
            int max = -1;
            ServerThread bestThread = null;
            for (ServerThread s : Sto.serverThreads) {
                if (s.getPoeni() > max) {
                    max = s.getPoeni();
                    bestThread = s;
                }
            }
            System.out.println("\n[Server N0]: Pobednik sa najvise poena je  (" + bestThread.getPoeni() + ") igrac [" + bestThread.getIgracID() + "] sa tredom [" + bestThread.getSName() + "].");

            System.out.println("\nStatistika:");
            for (ServerThread s : Sto.serverThreads) {
                if (s.getIgracID() != null)
                    System.out.println("[Server N0]: [" + s.getSName() + "]: -> Igrac: [" + s.getIgracID() + "] - " + s.getPoeni() + " poeni");
            }
            System.out.println("\n\n");
        } else {
            if (Sto.koNajkraciIzvukao) Sto.seatSemaphore.release();
        }

    }
}
