package com.example.hello2;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.*;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("INFO","resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("INFO","start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("INFO","pause");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.txtHello);
        tv.setText("Hello from the code !");


        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;

        ImageView iv = (ImageView) findViewById(R.id.chat);

        Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.chat, opt);

        iv.setImageBitmap(img);

        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button_gray);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inMutable = true;

                ImageView iv = (ImageView) findViewById(R.id.chat);

                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.chat, opt);

                iv.setImageBitmap(img);

                toGray(img);
            }
        });

        final Button button_hsv = findViewById(R.id.button_hsv);
        button_hsv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inMutable = true;

                ImageView iv = (ImageView) findViewById(R.id.chat);

                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.chat, opt);

                iv.setImageBitmap(img);

                colorize(img);
            }
        });


        System.out.println("salut les nazes");
        Log.i("INFO","yo!");
    }

    void toGray(Bitmap bmp) {

        int sizex = bmp.getWidth();
        int sizey = bmp.getHeight();
        int pixelcolor;


        for (int x = 0 ; x< sizex ; x++){
            for (int y =0 ; y<sizey; y++) {
                pixelcolor = bmp.getPixel(x,y);
                int new_pixelcolor = (int)(Color.red(pixelcolor)*0.3+Color.green(pixelcolor)*0.59+Color.blue(pixelcolor)*0.11);
                bmp.setPixel(x, y, Color.rgb(new_pixelcolor,new_pixelcolor,new_pixelcolor));
            }
        }
    }

    void colorize (Bitmap bmp){
        int sizex = bmp.getWidth();
        int sizey = bmp.getHeight();
        int pixelcolor;
        float t = 0;
        float s = 0;
        float v;
        float hsv[] = new float[3];


        for (int x = 0 ; x< sizex ; x++){
            for (int y =0 ; y<sizey; y++) {

                pixelcolor = bmp.getPixel(x,y);
                float r = Color.red(pixelcolor)/255.f;
                float g = Color.green(pixelcolor)/255.f;
                float b = Color.blue(pixelcolor)/255.f;

                float min = Math.min(Math.min(r,g),b);
                float max = Math.max(Math.max(r,g),b);
                v = max;

                if (max==min){
                    t = 0;
                }
                if(max == r){
                    t = (60*((g-b)/(max-min ))+360) % 360;
                }
                if (max == g){
                    t = (60*((b-r)/(max-min))+120);
                }
                if (max == b){
                    t = (60*((r-g)/(max-min))+240);
                }
                if (max == 0){
                    s = 0;
                }
                else {
                    s = 1 - (min / max);
                }
                Random rd = new Random();
                float hue = rd.nextFloat();

                hsv[0] = hue;
                hsv[1] = s;
                hsv[2] = v;

                bmp.setPixel(x,y,Color.HSVToColor(hsv));

            }
        }
    }
}
