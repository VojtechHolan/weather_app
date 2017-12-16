package com.example.holas.weather_app;

/**
 * Created by Holas on 16.12.2017.
 */

public class City {
    String name;
    String lat;
    String lot;


    public City(String name, String lat, String lot){
        this.name = name;
        this.lat = lat;
        this.lot = lot;
    }


    public String getName(){
        return this.name;
    }
    public String getLat(){
        return this.lat;
    }
    public String getLon(){
        return this.lot;
    }
}
