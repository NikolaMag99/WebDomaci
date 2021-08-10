package http.response;

public class RedirectResponse  extends Response {

    private String lokacija;


    public RedirectResponse(String lokacija) {
        this.lokacija = lokacija;
    }
    @Override
    public String getResponseString() {
        String response = "HTTP/1.1 301 Redirect\r\nLocation: " + this.lokacija + "\r\n\r\n";
        return response;
    }
}
