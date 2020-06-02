package service.api;

import model.HttpUtilResponse;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class DadataHandler {


    public HttpUtilResponse sendRequest(String addr) {

        HttpRequest request = buildRequest(addr);

        return HttpUtil.sendRequest(request);

    }


    private HttpRequest buildRequest(String addr) {
        return HttpRequest.newBuilder()
                .uri(URI.create("https://cleaner.dadata.ru/api/v1/clean/address"))
                .POST(HttpRequest.BodyPublishers.ofString("[ \"" + addr + "\" ]"))
                .setHeader("Content-Type", "application/json; charset=utf-8")
                .setHeader("X-Secret", System.getenv("DADATA-SECRET"))
                .setHeader("Authorization", "Token " + System.getenv("DADATA-TOKEN"))
                .timeout(Duration.of(120, SECONDS))
                .build();
    }

}
