package service.api;

import model.HttpUtilResponse;
import model.testapi.Album;
import service.parser.GsonParser;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;


public class TestApiHandler {


    public String sendRequest() {

        HttpRequest request = buildRequest();

        HttpUtilResponse httpUtilResponse = HttpUtil.sendRequest(request);

        if (httpUtilResponse.getStatus() == 200) {
            Album[] albums = GsonParser.parseTestResponse(httpUtilResponse.getBody());
            return "API (https://jsonplaceholder.typicode.com/albums) works, the last element of json array is: \n" + albums[albums.length-1];
        } else {
            return "TestApi_Bad request. Status code = " + httpUtilResponse.getStatus();
        }

    }

    private HttpRequest buildRequest() {
        return HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create("https://jsonplaceholder.typicode.com/albums"))
                .timeout(Duration.of(120, SECONDS))
                .build();
    }

}
