package model.dadata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @Expose
    @SerializedName("result")
    private final String full_address;

    @Expose
    @SerializedName("geo_lat")
    private final Double latitude;

    @Expose
    @SerializedName("geo_lon")
    private final Double longitude;

    public Address(String full_address, Double latitude, Double longitude) {
        this.full_address = full_address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getFull_address() {
        return full_address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }


    @Override
    public String toString() {
        return ">>> АДРЕС: " + full_address + " (latitude=" + latitude + ", longitude=" + longitude + ")";
    }
}
