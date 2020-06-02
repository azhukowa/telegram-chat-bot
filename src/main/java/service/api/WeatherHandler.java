package service.api;

import model.HttpUtilResponse;
import model.dadata.Address;
import service.parser.GsonParser;

import java.util.Arrays;

public class WeatherHandler {

    DadataHandler dadataHandler;
    YandexWeatherHandler yandexWeatherHandler;

    public WeatherHandler(DadataHandler dadataHandler, YandexWeatherHandler yandexWeatherHandler) {
        this.dadataHandler = dadataHandler;
        this.yandexWeatherHandler = yandexWeatherHandler;
    }

    public String processAddress(String addr) {

        HttpUtilResponse httpUtilResponse = dadataHandler.sendRequest(addr);

        if(httpUtilResponse.getStatus() == 200){
            Address[] addresses = GsonParser.parseDadataResponse(httpUtilResponse.getBody());
            return processWeather(addresses);
        } else {
            return "DadataApi_Bad request. Status code = " + httpUtilResponse.getStatus();
        }


    }

    private String processWeather(Address[] addresses) {

        StringBuilder returnedText = new StringBuilder();

        Long numberOfAddresses = Arrays.stream(addresses)
                .filter(a -> a.getLatitude() != null && a.getLongitude() != null && a.getFull_address() != null)
                .peek(a -> returnedText.append(a.toString()).append("\n")
                        .append(
                                yandexWeatherHandler.sendRequest(a.getLatitude(), a.getLongitude())
                        ))
                .count();


        if (numberOfAddresses == 0) {
            returnedText.append("Unable to get the weather for this place");
        }

        return returnedText.toString();

    }


}
