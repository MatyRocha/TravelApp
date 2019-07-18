package com.verisk.g2.take_home_test.dto;

public class Airline {
    private String name;
    private String code2;
    private String code3;
    private String country;

    public Airline(String name, String code2, String code3, String country) {
        this.name = name;
        this.code2 = code2;
        this.code3 = code3;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "name='" + name + "'" +
                ", code2='" + code2 + "'" +
                ", code3='" + code3 + "'" +
                ", country='" + country + "'" +
                '}';
    }
}
