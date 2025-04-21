package com.customer.model;

public class Customer {
    private Long cId;
    private String firstName;
    private String lastName;
    private String country;

    public Customer() {
    }

    public Customer(Long cId, String firstName, String lastName, String country) {
        this.cId = cId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
