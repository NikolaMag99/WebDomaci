package server2;

import app.NewsletterController;
import http.HttpMethod;
import http.Request;
import http.response.Response;

public class RequestHandler2 {
    public Response handle(Request request) throws Exception {
        if (request.getPath().equals("/citatDana") && request.getHttpMethod().equals(HttpMethod.GET)) {
            return (new NewsletterController2(request)).doGet();
        }
        throw new Exception("Page: " + request.getPath() + ". Method: " + request.getHttpMethod() + " not found!");
    }
}
