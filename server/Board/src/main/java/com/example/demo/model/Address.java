package com.example.demo.model;

public class Address {
    public String address;
    public Double lat;
    public Double lon;

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public Double getLat(){
        return lat;
    }

    public Double getLon(){
        return lon;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Address(String address, Double lat, Double lon){
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }
}
