package com.example.holas.weather_app;

import android.content.Context;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button getWeather;
    EditText mesto;
    Context context = MainActivity.this;
    String whichButton = "";
    public static String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeather = (Button)findViewById(R.id.getWeather);
        mesto = (EditText) findViewById(R.id.city);

        getWeather.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.getWeather:
                whichButton = "today";
                break;

        }

        String mestoStr = mesto.getText().toString();
        fetchData process = new fetchData(mestoStr, context, whichButton);
        process.execute();

    }
}


