package com.example.holas.weather_app;

import android.content.Intent;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class todayWeather extends AppCompatActivity implements View.OnClickListener, SensorListener {

    TextView mestoI, datumI,popisI,popisSmallI,tempI, tempMinI, tempMaxI;
    ImageView ikonkaI, ikonkaTemp;
    String mesto;
    String datum;
    String ikonka;
    String popis;
    String popisSmall;
    String temp;
    String tempMin;
    String tempMax;

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
        tempI = (TextView) findViewById(R.id.temp);
        tempMinI = (TextView) findViewById(R.id.tempMin);
        tempMaxI = (TextView) findViewById(R.id.tempMax);

        Intent intent = getIntent();
        String window = intent.getStringExtra("window");
        mesto = intent.getStringExtra("mesto");
        datum = intent.getStringExtra("datum");
        ikonka = intent.getStringExtra("ikonka");
        popis = intent.getStringExtra("popis");
        popisSmall = intent.getStringExtra("popisSmall");
        temp = intent.getStringExtra("temp");
        tempMin = intent.getStringExtra("tempMin");
        tempMax = intent.getStringExtra("tempMax");

        mestoI.setText(mesto);
        datumI.setText(datum);
        vyberIkonky(ikonka,ikonkaI);
        popisI.setText(popis);
        popisSmallI.setText(popisSmall);
        ikonkaTemp.setImageResource(R.drawable.temp);
        tempI.setText(prevodNaC(temp));
        tempMinI.setText(prevodNaC(tempMin));
        tempMaxI.setText(prevodNaC(tempMax));

        Button getGraph;
        getGraph = (Button)findViewById(R.id.button_canvas);
        getGraph.setOnClickListener(this);


        SensorManager sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorMgr.registerListener(this,SensorManager.SENSOR_ACCELEROMETER,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onClick(View view) {

        Intent myIntent = new Intent(this,activity_canvas.class);
        myIntent.putExtra("temp", prevodNaC(temp));
        this.startActivity(myIntent);

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

    public String prevodNaC(String teplota){
        double cisloTeplota = Math.floor(Double.parseDouble(teplota) - 273.15);
        return String.valueOf(cisloTeplota);
    }

    private static final int SHAKE_THRESHOLD = 1500;
    long lastUpdate;

    float x;
    float y;
    float z;

    float last_x;
    float last_y;
    float last_z;

    public void onSensorChanged(int sensor, float[] values) {

        if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = values[SensorManager.DATA_X];
                y = values[SensorManager.DATA_Y];
                z = values[SensorManager.DATA_Z];

                float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }
    @Override
    public void onAccuracyChanged(int i, int i1) {

    }

}
