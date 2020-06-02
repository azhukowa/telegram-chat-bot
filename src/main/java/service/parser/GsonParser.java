package service.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.dadata.Address;
import model.testapi.Album;
import model.yandex.Weather;

public class GsonParser {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Album[] parseTestResponse(String response) {
        return gson.fromJson(response, Album[].class);
    }


    public static Address[] parseDadataResponse(String response) {
        return gson.fromJson(response, Address[].class);
    }

    public static Weather parseYandexWeatherResponse(String response) {
        return gson.fromJson(response, Weather.class);
    }


}
