package model.yandex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fact {

    @Expose
    @SerializedName("temp")
    private final Double temp;

    @Expose
    @SerializedName("feels_like")
    private final Double feels_like;

    @Expose
    @SerializedName("condition")
    private final String condition;


    public Fact(Double temp, Double feels_like, String condition) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Температура: " + temp + '\n' +
                "Ощущается как: " + feels_like + '\n' +
                "Состояние: " + conditionTranslator(condition) + '\n';
    }

    private String conditionTranslator(String conditionEng) {
        switch (conditionEng) {
            case "clear":
                return "ясно";
            case "partly-cloudy":
                return "малооблачно";
            case "cloudy":
                return "облачно с прояснениями";
            case "overcast":
                return "пасмурно";
            case "partly-cloudy-and-light-rain":
            case "cloudy-and-light-rain":
            case "overcast-and-light-rain":
                return "небольшой дождь";
            case "partly-cloudy-and-rain":
            case "cloudy-and-rain":
                return "дождь";
            case "overcast-and-rain":
                return "сильный дождь";
            case "overcast-thunderstorms-with-rain":
                return "сильный дождь, гроза";
            case "overcast-and-wet-snow":
                return "дождь со снегом";
            case "partly-cloudy-and-light-snow":
            case "cloudy-and-light-snow":
            case "overcast-and-light-snow":
                return "небольшой снег";
            case "partly-cloudy-and-snow":
            case "cloudy-and-snow":
                return "снег";
            case "overcast-and-snow":
                return "снегопад";
            default:
                return "не определено";
        }
    }
}
