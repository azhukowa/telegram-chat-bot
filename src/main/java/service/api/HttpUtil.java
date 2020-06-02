package service.api;

import model.HttpUtilResponse;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpUtil {

    final static HttpClient httpClient = HttpClient.newHttpClient();

    public static HttpUtilResponse sendRequest(HttpRequest request){

        String body;
        int statusCode;

        try {
            CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            body = response.thenApply(HttpResponse::body).get();
            statusCode = response.thenApply(HttpResponse::statusCode).get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return new HttpUtilResponse(statusCode, body);

    }

}
