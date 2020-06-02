package service.api;

import model.HttpUtilResponse;
import service.parser.GsonParser;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class YandexWeatherHandler {


    public String sendRequest(Double latitude, Double longitude) {

        HttpRequest request = buildRequest(latitude, longitude);

        HttpUtilResponse httpUtilResponse = HttpUtil.sendRequest(request);

        if (httpUtilResponse.getStatus() == 200) {
            return GsonParser.parseYandexWeatherResponse(httpUtilResponse.getBody()).toString();
        } else {
            return "YandexApi_Bad request. Status code = " + httpUtilResponse.getStatus();
        }


    }


    private HttpRequest buildRequest(Double latitude, Double longitude) {
        return HttpRequest.newBuilder()
                .uri(URI.create("https://api.weather.yandex.ru/v1/forecast?lat=" + latitude + "&lon=" + longitude + "&lang=ru_RU"))
                .GET()
                .setHeader("Content-Type", "application/json; charset=utf-8")
                .setHeader("X-Yandex-API-Key", System.getenv("YANDEX-TOKEN"))
                .timeout(Duration.of(120, SECONDS))
                .build();
    }

}
