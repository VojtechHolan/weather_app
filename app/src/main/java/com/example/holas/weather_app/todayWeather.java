package com.example.holas.weather_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class todayWeather extends AppCompatActivity {

    TextView mestoI, datumI,popisI,popisSmallI;//tempI, tempMinI, tempMaxI;
    ImageView ikonkaI, ikonkaTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_weather);

        mestoI = (TextView) findViewById(R.id.mesto);
        datumI = (TextView) findViewById(R.id.datum);
        ikonkaI = (ImageView) findViewById(R.id.ikonka);
        popisI = (TextView) findViewById(R.id.popis);
        popisSmallI = (TextView) findViewById(R.id.popisSmall);
        ikonkaTemp = (ImageView) findViewById(R.id.ikonkaTemp);
       /* tempI = (TextView) findViewById(R.id.temp);
        tempMinI = (TextView) findViewById(R.id.tempMin);
        tempMaxI = (TextView) findViewById(R.id.tempMax);*/

        Intent intent = getIntent();
        String mesto = intent.getStringExtra("mesto");
        String datum = intent.getStringExtra("datum");
        String ikonka = intent.getStringExtra("ikonka");
        String popis = intent.getStringExtra("popis");
        String popisSmall = intent.getStringExtra("popisSmall");
        String temp = intent.getStringExtra("temp");
        String tempMin = intent.getStringExtra("tempMin");
        String tempMax = intent.getStringExtra("tempMax");

        mestoI.setText(mesto);
        datumI.setText(datum);
        vyberIkonky(ikonka,ikonkaI);
        popisI.setText(popis);
        popisSmallI.setText(popisSmall);
        ikonkaTemp.setImageResource(R.drawable.temp);
        /*tempI.setText(temp);
        tempMinI.setText(tempMin);
        tempMaxI.setText(tempMax);*/

    }

    public void vyberIkonky(String enter, ImageView image){

        switch(enter) {
            case "01d":
                image.setImageResource(R.drawable.a);
                break;
            case "04d":
                image.setImageResource(R.drawable.b);
                break;
            case "04n":
                image.setImageResource(R.drawable.b);
                break;
            case "02n":
                image.setImageResource(R.drawable.d);
                break;
            case "11n":
                image.setImageResource(R.drawable.e);
                break;
            case "09d":
                image.setImageResource(R.drawable.f);
                break;
            case "13d":
                image.setImageResource(R.drawable.g);
                break;
            case "02d":
                image.setImageResource(R.drawable.h);
                break;
            case "09n":
                image.setImageResource(R.drawable.f);
                break;
            case "13n":
                image.setImageResource(R.drawable.g);
                break;
            case "11d":
                image.setImageResource(R.drawable.e);
                break;
            case "03d":
                image.setImageResource(R.drawable.k);
                break;
            case "10d":
                image.setImageResource(R.drawable.l);
                break;
            case "50d":
                image.setImageResource(R.drawable.m);
                break;
            case "01n":
                image.setImageResource(R.drawable.n);
                break;
            case "03n":
                image.setImageResource(R.drawable.o);
                break;
            case "10n":
                image.setImageResource(R.drawable.p);
                break;
            case "50n":
                image.setImageResource(R.drawable.m);
                break;
        }
    }

}
