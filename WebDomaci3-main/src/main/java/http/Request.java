package http;

import java.util.HashMap;

public class Request {

    private final HttpMethod httpMethod;

    private final String path;

    private HashMap<String, String> params = null;

    public Request(HttpMethod httpMethod, String path, HashMap<String,String> mapa) {
        this.params = mapa;
        this.httpMethod = httpMethod;
        this.path = path;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }
}
