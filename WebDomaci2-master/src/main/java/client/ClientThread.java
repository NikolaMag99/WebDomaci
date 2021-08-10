package client;

import com.google.gson.Gson;
import model.Request;
import model.Response;
import model.Status;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.UUID;

public class ClientThread implements Runnable {
    private String ime;
    private String id;
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private Gson gson;

    private int brojMesta;
    private boolean seated = false;

    private Response response;
    private Request request;

    private boolean flag = false;
    private String s = "Igrac";

    public ClientThread(String ime) throws IOException {
        this.ime = ime;
        socket = new Socket("localhost", 9000);
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        gson = new Gson();
        id = s + UUID.randomUUID().toString();

        response = new Response();
        request = new Request();
    }

    public void run() {
        flag = true;

        request.setStatus(Status.CONNECT);
        request.setData(id);
        request();

        while (flag) {
            response();

            if (response.getStatus().equals(Status.CONNECTED)) {
                brojMesta = ((Double) response.getData()).intValue();
                System.out.println("[" + ime + "]: " + "Igrac je primio CONNECT Status [" + id + "].Igrac je seo na mesto " + brojMesta + ".");
                request.setStatus(Status.READY);
                request();

            } else if (response.getStatus().equals(Status.TIMEOUT)) {
                System.out.println("[" + ime + "]: " + "TIMEOUT za igraca [" + id + "]");
                flag = false;

            } else if (response.getStatus().equals(Status.BID)) {
                boolean guess = (new Random()).nextBoolean();
                request.setStatus(Status.BID);
                request.setData(guess);
                System.out.println("[" + ime + "]: " + "Primio BID Status  [" + id + "]. Igrac biduje " + guess + ".");
                request();

            } else if (response.getStatus().equals(Status.DRAW)) {
                int draw = (new Random()).nextInt(6) + 1;
                request.setStatus(Status.DREW);
                request.setData(draw);
                System.out.println("[" + ime + "]: " + "Primio DRAW Status [" + id + "]. Igrac vuce stapic " + draw + ".");
                request();

            } else if (response.getStatus().equals(Status.FINISHED)) {
                System.out.println("[" + ime + "]: " + "Izbacen je iz igre igrac [" + id + "]");
                flag = false;

            }

        }

        System.out.println("[" + ime + "]: " + "ISPAO -> [" + id + "]");

    }

    private Request request() {
        try {
            out.write(gson.toJson(request));
            out.newLine();
            out.flush();
            return request;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Response response() {
        try {
            String json = in.readLine();
            response = gson.fromJson(json, Response.class);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
