package com.example.holas.weather_app;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Holas on 25.11.2017.
 */

public class fetchData extends AsyncTask<Void, Void, Void>{
    String data = "";
    String mesto = "";
    String whichButton = "";
    Context context = null;
    Pocasi p;


    public fetchData(String mesto, Context context,String whichButton){
        this.mesto = mesto;
        this.context = context;
        this.whichButton = whichButton;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=" + this.mesto + "&cnt=14&APPID=a486321041c407e929e85180c002590a");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data += line;
            }
            data  = data.substring(0,data.length()-4);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        try {
            JSONObject reader = new JSONObject(this.data);

            /*Mesto*/
            JSONObject mestoO  = reader.getJSONObject("city");
            String mesto = mestoO.get("name").toString();

            JSONArray dateArr = reader.getJSONArray("list");

            //Připraveny cyklus
            //for (int i = 0; i < dateArr.length(); ++i) {
            //}
            JSONObject day = null;
            if(whichButton =="today"){
                day = dateArr.getJSONObject(0);
            }

            //Datum
            String datumStr = day.getString("dt");

            Date date = new Date(Long.parseLong(datumStr)*1000);
            String formatedDate = new SimpleDateFormat("dd.MM.yyyy").format(date);


            //Ikonka
            JSONArray weatherArr = day.getJSONArray("weather");
            JSONObject o = weatherArr.getJSONObject(0);
            String ikonka = o.getString("icon");

            //Popisky
            String popis = o.getString("main");
            String popisSmall = o.getString("description");

            /*//Teplota
            JSONArray tempArr = day.getJSONArray("temp");
            JSONObject oo = tempArr.getJSONObject(0);

            String tempMin = oo.getString("min");
            String temp = oo.getString("day");
            String tempMax = oo.getString("max");*/


            p = new Pocasi(mesto,formatedDate,ikonka, popis,popisSmall/*, temp, tempMin,tempMax*/);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(whichButton == "today"){
            Intent myIntent = new Intent(context, todayWeather.class);
            myIntent.putExtra("mesto", p.mesto);
            myIntent.putExtra("datum", p.datum);
            myIntent.putExtra("ikonka", p.ikonka);
            myIntent.putExtra("popis", p.popis);
            myIntent.putExtra("popisSmall", p.popisSmall);
            /*myIntent.putExtra("tempMin", p.teplotaMin);
            myIntent.putExtra("temp", p.teplota);
            myIntent.putExtra("tempMax", p.teplotaMax);*/
            context.startActivity(myIntent);
        }
        //Toast.makeText(context,p.teplota ,Toast.LENGTH_LONG).show();


    }
}
