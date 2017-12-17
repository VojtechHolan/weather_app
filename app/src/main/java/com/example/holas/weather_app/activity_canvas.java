package com.example.holas.weather_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class activity_canvas extends AppCompatActivity {

    public String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        temp = intent.getStringExtra("temp");

        setContentView(new Graph(this, temp));

        Toast.makeText(this, temp , Toast.LENGTH_LONG).show();



    }



}
