package http;

import app.RequestHandler;
import http.response.Response;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ServerThread implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket sock) {
        this.client = sock;

        try {
            //inicijalizacija ulaznog toka
            in = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));

            //inicijalizacija izlaznog sistema
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    client.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            // uzimamo samo prvu liniju zahteva, iz koje dobijamo HTTP method i putanju
            String requestLine = in.readLine();

            StringTokenizer stringTokenizer = new StringTokenizer(requestLine);

            String method = stringTokenizer.nextToken();
            String path = stringTokenizer.nextToken();

            System.out.println("\nHTTP ZAHTEV KLIJENTA:\n");
            do {
                System.out.println(requestLine);
                requestLine = in.readLine();
            } while (!requestLine.trim().equals(""));

            HashMap<String,String> ubaci = new HashMap<>();
            if (method.equals(HttpMethod.POST.toString())) {
               char[] bafer = new char[2048];
               in.read(bafer);
               String parametri = new String(bafer);
               System.out.println(parametri);
               String[] arej = parametri.split("&");
               for( String key: arej) {
                   ubaci.put(key.split("=")[0], URLDecoder.decode(key.split("=")[1], StandardCharsets.UTF_8.name()));
               }
            }

            Request request = new Request(HttpMethod.valueOf(method), path, ubaci);

            RequestHandler requestHandler = new RequestHandler();
            Response response = requestHandler.handle(request);

            System.out.println("\nHTTP odgovor:\n");
            System.out.println(response.getResponseString());

            out.println(response.getResponseString());

            in.close();
            out.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
