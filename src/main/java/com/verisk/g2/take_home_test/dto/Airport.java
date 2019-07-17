package com.verisk.g2.take_home_test.dto;

public class Airport {
    private String name;
    private String city;
    private String country;
    private String iata3;
    private String latitude;
    private String longitude;

    public Airport(String name, String city, String country, String iata3, String  latitude, String longitude) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.iata3 = iata3;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIata3() {
        return iata3;
    }

    public void setIata3(String iata3) {
        this.iata3 = iata3;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
