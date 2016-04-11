package com.maxmommersteeg.max.android_final.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Max on 3-3-2016.
 */
@SuppressWarnings("serial")
public class Location implements Serializable {
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("city")
    @Expose
    private String city;

    public Location(Double latitude, Double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        if(latitude > 90 || latitude < -90)
            return;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        if(longitude > 180 || longitude < -180)
            return;
        this.longitude = longitude;
    }
}
