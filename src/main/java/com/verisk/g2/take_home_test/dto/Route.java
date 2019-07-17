package com.verisk.g2.take_home_test.dto;

public class Route {
    private String airline;
    private String origin;
    private String destination;

    public Route(String airline, String origin, String destination) {
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
    }

    public String  getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Route{" +
                "airline=" + airline +
                ", origin=" + origin +
                ", destination=" + destination +
                '}';
    }
}
