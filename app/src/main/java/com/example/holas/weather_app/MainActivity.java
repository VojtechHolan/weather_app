package com.example.holas.weather_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        String mestoStr = mesto.getText().toString();
        fetchData process = new fetchData(mestoStr);
        process.execute();

       /* JSONArray arr = new JSONArray(data);
        JSONObject jObj = arr.getJSONObject(0);
        String date = jObj.getString("NeededString");*/


        Toast toast = Toast.makeText(this, data, Toast.LENGTH_LONG);
        toast.show();
    }
}
