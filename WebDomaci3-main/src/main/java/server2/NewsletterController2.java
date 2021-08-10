package server2;

import app.Controller;
import com.google.gson.Gson;
import http.Citati;
import http.Request;
import http.response.HtmlResponse;
import http.response.RedirectResponse;
import http.response.Response;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class NewsletterController2 extends Controller {


    String citat = "Dragoslav: " + "Svaki dan novi citat";
    String citatDana;
    PrintWriter out;
    BufferedReader in;
    Gson gson = new Gson();
    JsonResponse response;


    public NewsletterController2(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
            String citatDana = citat;
//        int size = citatDana.getKey().length() + citatDana.getValue().length();
//        String response = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-Length: "+ size + "\r\n\r\n" + gson.toJson(citatDana, Map.Entry.class);

        response = new JsonResponse(citatDana);
        return response;

    }

    @Override
    public Response doPost() {
       return null;
    }
}
