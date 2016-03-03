package com.maxmommersteeg.max.android_final.Model;

/**
 * Created by Max on 3-3-2016.
 */
public class Location {
    private Double latitude;
    private Double longitude;


    /*
    * Empty constructor
    */
    public Location() {

    }

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
