package model;

public class HttpUtilResponse {

    private final int status;
    private final String body;

    public HttpUtilResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }
}
