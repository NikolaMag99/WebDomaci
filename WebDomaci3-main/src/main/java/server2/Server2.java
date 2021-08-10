package server2;

import http.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {



    public static final int TCP_PORT = 8114;

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(TCP_PORT);
            while (true) {
                Socket sock = ss.accept();
                new Thread(new ServerThread2(sock)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}