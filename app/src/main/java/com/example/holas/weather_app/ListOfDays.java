package com.example.holas.weather_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ListOfDays extends AppCompatActivity {

    TextView mestoI;
    ListView listPocasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_days);

        mestoI = (TextView) findViewById(R.id.mesto);
        listPocasi = (ListView)findViewById(R.id.listPocasi);

        Intent intent = getIntent();
        String mesto = intent.getStringExtra("mesto");

        mestoI.setText(mesto);


        final List<Pocasi> arrayPocasi = (ArrayList<Pocasi>)getIntent().getSerializableExtra("weatherList");

        AdapterList myarrayAdapter = new AdapterList(this, R.layout.oneday, arrayPocasi);
        listPocasi.setAdapter(myarrayAdapter);
        listPocasi.setTextFilterEnabled(true);



        listPocasi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(ListOfDays.this, todayWeather.class);
                Pocasi p = arrayPocasi.get(position);
                myIntent.putExtra("window","long");
                myIntent.putExtra("mesto", p.mesto);
                myIntent.putExtra("datum", p.datum);
                myIntent.putExtra("ikonka", p.ikonka);
                myIntent.putExtra("popis", p.popis);
                myIntent.putExtra("popisSmall", p.popisSmall);
                myIntent.putExtra("tempMin", p.teplotaMin);
                myIntent.putExtra("temp", p.teplota);
                myIntent.putExtra("tempMax", p.teplotaMax);
                myIntent.putExtra("window","today");
                startActivity(myIntent);
            }
        });
    }
}
