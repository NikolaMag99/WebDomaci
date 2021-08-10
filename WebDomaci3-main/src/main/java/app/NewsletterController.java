package app;

import com.google.gson.Gson;
import http.Request;
import http.Citati;
import http.response.HtmlResponse;
import http.response.RedirectResponse;
import http.response.Response;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class NewsletterController extends Controller {


    HashMap<String, String> citat;
    String citatDana;
    PrintWriter out;
    BufferedReader in;
    Gson gson = new Gson();
    String requestLine;
    public NewsletterController(Request request) {
        super(request);
        citat = Citati.getInstance().getCitati();
    }

    @Override
    public Response doGet() {
        String htmlBody = "" +
                "<form method=\"POST\" action = \"savequote\">" +
                "<label>Autor: </label><input name=\"autor\" type=\"text\"><br><br>" +
                "<label>Citat: </label><input name=\"citat\" type=\"text\"><br><br>" +
                "<button type = \"submit\">Dodaj citat</button>" +
                "</form>" +
                "<form>" +
                "<h1>Sacuvani citati</h1>";

        for (Map.Entry<String, String> m : citat.entrySet()) {
            htmlBody += "<br><br>" +
                    m.getKey() + " " + '"' + m.getValue() + '"' + "<br>";
        }
        htmlBody += "</form>";

        try {
            Socket client = new Socket("localhost", 8114);
            //inicijalizacija ulaznog toka
            in = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));
            //inicijalizacija izlaznog sistema
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    client.getOutputStream())), true);
            //saljem zahtev za drugi server
            out.println("GET /citatDana HTTP/1.1\n" +
                    "Accept: application/json\r\n\r\n");
            requestLine = in.readLine();
            do {
                System.out.println(requestLine);
                requestLine = in.readLine();
            } while (!requestLine.trim().equals(""));

          String mapaDana = gson.fromJson(in.readLine(), String.class);


            htmlBody += "<h1>Citat Dana</h1>\n" + "<h2>" + mapaDana + "</h2>";


        } catch (IOException e) {

            e.printStackTrace();
        }

        String content = "<html><head><title>Odgovor servera</title></head>\n";
        content += "<body>" + htmlBody + "</body></html>";

        return new HtmlResponse(content);
    }

    @Override
    public Response doPost() {
        String autor = request.getParams().get("autor");
        String citatAutora = request.getParams().get("citat");
        citat.put(autor, citatAutora);
        return new RedirectResponse("/citati");
    }
}
