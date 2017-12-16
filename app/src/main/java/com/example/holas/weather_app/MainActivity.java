package com.example.holas.weather_app;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button getWeather, getWeatherLong;
    FloatingActionButton favorite;
    EditText mesto;
    Context context = MainActivity.this;
    String whichButton = "";
    public static String data;
    private String[] parts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeather = (Button)findViewById(R.id.getWeather);
        getWeatherLong = (Button)findViewById(R.id.getWeatherLong);
        mesto = (EditText) findViewById(R.id.city);
        favorite = (FloatingActionButton) findViewById(R.id.favorite);

        getWeather.setOnClickListener(this);
        getWeatherLong.setOnClickListener(this);
        favorite.setOnClickListener(this);


        /*Plneni tabulky daty*/
        InputStream is = this.getResources().openRawResource(R.raw.cities);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        ArrayList<String> cityList = new ArrayList<String>();

        try {
            String line = reader.readLine();
            while(line != null){
                cityList.add(line);
                line = reader.readLine(); // název
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        DatabaseHandler db = new DatabaseHandler(this);
        db.resetCityTable();
        for (int i = 0; i < cityList.size(); i++){
            String line = cityList.get(i);
            String[] parts = line.split(" ");
            
            City mesto = new City(parts[1],parts[2],parts[3]);
            db.addCity(mesto);
        }



        Toast.makeText(this, db.getCity(1).getLat() , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {

        String mestoStr;
        fetchData process;

        switch(view.getId()){
            case R.id.getWeather:
                whichButton = "today";
                mestoStr = mesto.getText().toString();
                process = new fetchData(mestoStr, context, whichButton);
                process.execute();
                break;
            case R.id.getWeatherLong:
                whichButton = "long";
                mestoStr = mesto.getText().toString();
                process = new fetchData(mestoStr, context, whichButton);
                process.execute();
                break;
            case R.id.favorite:
                whichButton = "Favorite";
                Intent myIntent = new Intent(context,myFavorite.class);
                context.startActivity(myIntent);
            break;
        }
    }
}


