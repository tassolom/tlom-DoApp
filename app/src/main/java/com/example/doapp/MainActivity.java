package com.example.doapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private Bitmap logoBitmap;
    private ImageView logo;
    private int mDelay = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = (ImageView) findViewById(R.id.logoDoApp);
        logoBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        logo.setImageBitmap(logoBitmap);

        Intent startLogin = new Intent(MainActivity.this, loginScreen.class);
        startActivity(startLogin);
    }
}
