package com.company;

import java.io.Serializable;

public class Subscriber implements Serializable {
    private String firstName;
    private String lastName;
    private String city;
    private int phone;

    public Subscriber(String firstName, String lastName, String city, int phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", phone=" + phone +
                '}';
    }
}
