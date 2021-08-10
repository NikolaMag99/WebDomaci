package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class ServerMain {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9000);
        int maxBrojRundi;
        int serverThreadBrojac = 1;

        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(6, new Runda());
        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(6, new Skor());

        System.out.print("[Server T0]: Unesite maksimalan broj rundi: ");
        Scanner sc = new Scanner(System.in);
        maxBrojRundi = sc.nextInt();
        sc.close();

        System.out.println("\n[Server T0]:Server startovao na portu : " + 9000 + "\n");
        Sto server = new Sto(maxBrojRundi);

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread("Server N" + serverThreadBrojac++, socket, cyclicBarrier1, cyclicBarrier2);
                serverThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
