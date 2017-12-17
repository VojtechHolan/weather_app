package com.example.holas.weather_app;

/**
 * Created by Holas on 16.12.2017.
 */

public class City {
    String name;
    String lot;
    String lat;



    public City(String name, String lot, String lat){
        this.name = name;
        this.lat = lot;
        this.lot = lat;
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
