package com.maxmommersteeg.max.android_final.model;

import java.util.Date;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Max on 3-3-2016.
 */
public class Person {
    @SerializedName("personId")
    @Expose
    private Integer personId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("birthDate")
    @Expose
    private Date birthDate;
    @SerializedName("currentLocation")
    @Expose
    private Location currentLocation;

    public Person() {

    }

    public Integer getPersonId() {
        return this.personId;
    }

    public void setPersonId(Integer personid) {
        this.personId = personid;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middlename) {
        this.middleName = middlename;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthdate) {
        if(new Date().before(birthdate))
            return;
        this.birthDate = birthdate;
    }

    public Location getCurrentLocation() { return this.currentLocation; }

    public void setCurrentLocation(Double latitude, Double longitude) {
        if(currentLocation == null) {
            currentLocation = new Location(latitude, longitude);
            return;
        }
        currentLocation.setLatitude(latitude);
        currentLocation.setLongitude(longitude);
    }

    public void setCurrentLocation(Location location) {
        if(location == null)
            return;
        currentLocation = location;
    }

    public String getFullName() {
        if(middleName == null || middleName.isEmpty())
            return firstName + " " + lastName;
        return firstName + " " + middleName + " " + lastName;
    }
}
