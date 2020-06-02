package model.yandex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @Expose
    @SerializedName("now_dt")
    private final String responseTime;

    @Expose
    @SerializedName("fact")
    private final Fact fact;

    public Weather(String responseTime, Fact fact) {
        this.responseTime = responseTime;
        this.fact = fact;
    }


    @Override
    public String toString() {
        return ">>> ПОГОДА:" + '\n' + fact + '\n';
    }

}
