package com.example.holas.weather_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Holas on 27.11.2017.
 */

public class AdapterList extends ArrayAdapter<Pocasi> {

        Context context;
        int layoutResourceId;
        List<Pocasi> data;

        public AdapterList(Context context, int resource, List<Pocasi> objects) {
            super(context, resource, objects);
            this.layoutResourceId = resource;
            this.context = context;
            this.data = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            EntryHolder item = null;

            if(row == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                row = inflater.inflate(layoutResourceId, parent, false);

                item = new EntryHolder();
                item.datumList = (TextView) row.findViewById(R.id.datumList);
                item.teplotaList = (TextView) row.findViewById(R.id.teplotaList);
                item.ikonkaList = (ImageView) row.findViewById(R.id.ikonkaList);

                row.setTag(item);
            }
            else
            {
                item = (EntryHolder)row.getTag();
            }

            Pocasi entry = data.get(position);
            item.datumList.setText(entry.datum);
            item.teplotaList.setText(prevodNaC(entry.teplota));
            vyberIkonky(entry.ikonka,item.ikonkaList );

            return row;
        }

        class EntryHolder {
            TextView datumList;
            TextView teplotaList;
            ImageView ikonkaList;
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
        String finTemp = Double.toString(Math.abs(cisloTeplota));
        return finTemp;
    }

}
