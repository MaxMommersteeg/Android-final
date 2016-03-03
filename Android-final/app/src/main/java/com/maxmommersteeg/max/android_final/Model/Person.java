package com.maxmommersteeg.max.android_final.Model;

import java.util.Date;

/**
 * Created by Max on 3-3-2016.
 */
public class Person {

    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;

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
        if(new Date().after(birthdate))
            return;
        this.birthDate = birthdate;
    }
}
