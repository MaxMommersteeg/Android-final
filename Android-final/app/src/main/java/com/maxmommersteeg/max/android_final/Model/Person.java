package com.maxmommersteeg.max.android_final.Model;

import java.util.Date;

/**
 * Created by Max on 3-3-2016.
 */
public class Person {

    private Integer personId;

    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;

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
