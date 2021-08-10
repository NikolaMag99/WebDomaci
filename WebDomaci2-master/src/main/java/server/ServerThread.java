package server;

import com.google.gson.Gson;
import model.Request;
import model.Response;
import model.Status;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ServerThread extends Thread {
    private String ime;
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private Gson gson;

    private CyclicBarrier cyclicBarrier1;
    private CyclicBarrier cyclicBarrier2;

    private String igracID;
    private int brojMesta = -1;
    private int poeni = 0;

    private Response response;
    private Request request;

    private boolean flag = false;


    private boolean guess = false;


    public ServerThread(String ime, Socket socket, CyclicBarrier cyclicBarrier1, CyclicBarrier cyclicBarrier2) throws IOException {
        this.ime = ime;
        this.socket = socket;
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        this.gson = new Gson();

        response = new Response();
        request = new Request();

        this.cyclicBarrier1 = cyclicBarrier1;
        this.cyclicBarrier2 = cyclicBarrier2;
    }

    public void run() {
        flag = true;

        try {

            while (flag) {
                request();

                if (request.getStatus().equals(Status.CONNECT)) {
                    igracID = (String) request.getData();


                    if (!Sto.seatSemaphore.tryAcquire(2000, TimeUnit.MILLISECONDS)) {
                        response.setStatus(Status.TIMEOUT);
                        response();
                        System.out.println("[" + ime + "]: " + "Igrac je previseo cekao u semaforu. Zavrsio.");
                        return;
                    }


                    brojMesta = Sto.takeSeat(igracID, this);
                    if (brojMesta != -1) {
                        response.setStatus(Status.CONNECTED);
                        response.setData(brojMesta);
                        response();
                    }

                } else if (request.getStatus().equals(Status.READY)) {

                    while (flag) {

                        if (!prvaBarijera()) {
                            removeUser();
                            response();
                            return;
                        }
                        response();

                        request();

                        if (request.getStatus().equals(Status.BID)) {
                            guess = (Boolean) request.getData();
                            System.out.println("[" + ime + "]: " + "Igrac: [" + igracID + "] bid " + guess);
                            if (!drugaBarijera()) break;

                        } else if (request.getStatus().equals(Status.DREW)) {
                            int clientDrew = ((Double) request.getData()).intValue();
                            if (clientDrew == Sto.najkraciStapic) {
                                System.out.println("[" + ime + "]: " + "Igrac: [" + igracID + "] izvukao je najkraci stapic - " + clientDrew);
                                Sto.koNajkraciIzvukao = true;
                            } else {
                                System.out.println("[" + ime + "]: " + "Igrac: [" + igracID + "] vuce stapic " + clientDrew);
                                Sto.koNajkraciIzvukao = false;
                            }
                            if (!drugaBarijera()) break;

                        }
                    }
                    response.setStatus(Status.FINISHED);

                }

            }
            response();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //vraca false ako je timeouut 2 sekunde i ako je pukla
    private boolean prvaBarijera() {
        try {
            cyclicBarrier1.await(2000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("[" + ime + "]: " + "Previse cekao da igra pocne.");
            response.setStatus(Status.TIMEOUT);
            flag = false;
            return false;
        } catch (BrokenBarrierException e) {
            System.out.println("[" + ime + "]: " + "Barrier exception: Previse cekao da igra pocne.");
            response.setStatus(Status.TIMEOUT);
            flag = false;
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean drugaBarijera() {
        try {
            cyclicBarrier2.await(2000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("[" + ime + "]: " + "Previse cekao u drugoj barijeri.");
            response.setStatus(Status.TIMEOUT);
            flag = false;
            return false;
        } catch (BrokenBarrierException e) {
            System.out.println("[" + ime + "]: " + "Barrier exception: Previse cekao u drugoj barijeri.");
            response.setStatus(Status.TIMEOUT);
            flag = false;
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private synchronized void removeUser() {
        for (int i = 0; i < Sto.mestaZaStolom; i++) {
            if (Sto.igraci[i] != null && Sto.igraci[i].equals(igracID)) {
                Sto.igraci[i] = null;
                break;
            }
        }
        Sto.seatSemaphore.release();
    }

    private Response response() {
        try {
            out.write(gson.toJson(response));
            out.newLine();
            out.flush();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Request request() {
        try {
            String json = in.readLine();
            request = gson.fromJson(json, Request.class);
            return request;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSName() {
        return this.ime;
    }

    public void setSName(String name) {
        this.ime = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedWriter getOut() {
        return out;
    }

    public void setOut(BufferedWriter out) {
        this.out = out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public CyclicBarrier getCyclicBarrier1() {
        return cyclicBarrier1;
    }

    public void setCyclicBarrier1(CyclicBarrier cyclicBarrier1) {
        this.cyclicBarrier1 = cyclicBarrier1;
    }

    public CyclicBarrier getCyclicBarrier2() {
        return cyclicBarrier2;
    }

    public void setCyclicBarrier2(CyclicBarrier cyclicBarrier2) {
        this.cyclicBarrier2 = cyclicBarrier2;
    }

    public String getIgracID() {
        return igracID;
    }

    public void setIgracID(String igracID) {
        this.igracID = igracID;
    }

    public int getBrojMesta() {
        return brojMesta;
    }

    public void setBrojMesta(int brojMesta) {
        this.brojMesta = brojMesta;
    }

    public int getPoeni() {
        return poeni;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getGuess() {
        return guess;
    }

    public void setGuess(boolean guess) {
        this.guess = guess;
    }
}
