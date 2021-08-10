package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Sto {

    protected static int mestaZaStolom = 6;
    protected static String[] igraci;

    protected static int maxBrojRundi;
    protected static int currentIteration = 0;

    protected static int koIzvlaci = 0;
    protected static boolean koNajkraciIzvukao = false;
    protected static int najkraciStapic;

    protected static ArrayList<ServerThread> serverThreads = new ArrayList<>();

    protected static Semaphore seatSemaphore = new Semaphore(6);

    public Sto(int maxBrojRundi) throws IOException {
        this.maxBrojRundi = maxBrojRundi;
        igraci = new String[mestaZaStolom];
    }


    //vrati indeks slobodnog mesta, a ako nema -1
    public static synchronized int takeSeat(String playerId, ServerThread serverThread) {
        serverThreads.add(serverThread);
        for (int i = 0; i < mestaZaStolom; i++) {
            if (igraci[i] == null) {
                igraci[i] = playerId;
                return i;
            }
        }
        return -1;
    }

}
