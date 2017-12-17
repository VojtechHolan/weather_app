package com.example.holas.weather_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Holas on 16.12.2017.
 */

public class Graph extends View {

    public String temp;

    public Graph(Context context, String temp) {
        super(context);

        this.temp = temp;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        Paint text = new Paint();

        text.setColor(Color.rgb(0,0,0));
        text.setTextAlign(Paint.Align.CENTER);
        text.setTextSize(60);
        int textPos = 0;

        int widthWin = canvas.getWidth();
        int heightWin = canvas.getHeight();


        p.setColor(Color.GRAY);
        canvas.drawRect((widthWin/2)-200,(heightWin/2)-600,(widthWin/2)+200,(heightWin/2)+600,p);

        if(temp.charAt(0) == '-'){
            p.setColor(Color.BLUE);
            textPos = 65;

        }
        else{
            p.setColor(Color.RED);
            textPos = -5;
        }

        Double plus = Double.valueOf(temp)*10;
        int plusInt = plus.intValue();

        canvas.drawRect((widthWin/2)-200,(heightWin/2)-plusInt,(widthWin/2)+200,(heightWin/2),p);
        canvas.drawText(temp,(widthWin/2) ,(heightWin/2) - plusInt + textPos,text);


    }
}
