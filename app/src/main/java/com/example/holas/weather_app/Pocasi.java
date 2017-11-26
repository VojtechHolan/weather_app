package com.example.holas.weather_app;

/**
 * Created by Holas on 25.11.2017.
 */

public class Pocasi {
    String mesto;
    String datum;
    String ikonka;
    String popis;
    String popisSmall;
    String teplota;
    String teplotaMax;
    String teplotaMin;
    String pocasiIkon;
    String vlhkost;
    String preasure;
    String vitrRychlost;
    String vitrSmer;


    public Pocasi(String mesto, String datum, String ikonka,String popis, String popisSmall /*String teplota, String teplotaMin, String teplotaMax*/) {
        this.mesto = mesto;
        this.datum = datum;
        this.ikonka = ikonka;
        this.popis = popis;
        this.popisSmall = popisSmall;
        /*this.teplota = teplota;
        this.teplotaMin = teplotaMin;
        this.teplotaMax = teplotaMax;*/
    }
}
