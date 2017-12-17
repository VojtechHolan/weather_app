package com.example.holas.weather_app;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.ActivityCompat;

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

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String lattitude,longitude;
    DatabaseHandler db = null;

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


        db = new DatabaseHandler(this);
        db.resetCityTable();

        for (int i = 0; i < cityList.size(); i++){
            String line = cityList.get(i);
            String[] parts = line.split(";");
            
            City mesto = new City(parts[1],parts[3],parts[2]);
            db.addCity(mesto);
        }

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }

        //Toast.makeText(this, db.getCity(1).getLat() , Toast.LENGTH_LONG).show();

    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            double lattiAct = 0;
            double longiAct = 0;
            boolean havePosition = true;

            if (location != null) {
                lattiAct = location.getLatitude();
                longiAct = location.getLongitude();
            } else  if (location1 != null) {
                lattiAct = location1.getLatitude();
                longiAct = location1.getLongitude();
            } else  if (location2 != null) {
                lattiAct = location1.getLatitude();
                longiAct = location1.getLongitude();
            }else{
                havePosition = false;
                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();
            }

            if(havePosition){

                double max = 0;
                String maxCity = "";

                Location loc1 = new Location("");
                loc1.setLatitude(lattiAct);
                loc1.setLongitude(longiAct);

                for (int i = 1;i < db.countOfRows()+1;i++){

                    Location loc2 = new Location("");
                    loc2.setLatitude(Double.parseDouble(db.getCity(i).getLat()));
                    loc2.setLongitude(Double.parseDouble(db.getCity(i).getLon()));

                    double distanceInMeters = loc1.distanceTo(loc2);


                    if(i == 1){
                        max = distanceInMeters;
                        maxCity = db.getCity(i).getName();
                    }else if(distanceInMeters < max){
                        max = distanceInMeters;
                        maxCity = db.getCity(i).getName();
                    }
                }

                mesto.setText(maxCity);
            }
            //Toast.makeText(this, String.valueOf(db.countOfRows()) ,Toast.LENGTH_SHORT).show();
        }
    }

    protected void buildAlertMessageNoGps(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
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




