package com.example.rickyandmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.rickyandmorty.databinding.ActivityMainBinding;
import com.example.rickyandmorty.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding dataBinder;

    private String openingString;
    private SharedPreferences sp;
    private SharedPreferences.Editor e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinder = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        sp = getSharedPreferences("OpeningInfo", MODE_PRIVATE);
        e = sp.edit();
        openingString = sp.getString("openingString", "Welcome!");
        dataBinder.setOpeningString(openingString);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent splashToMain = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(splashToMain);

                sp = getSharedPreferences("OpeningInfo",MODE_PRIVATE);
                e = sp.edit();
                e.putString("openingString", "Hello!");
                e.commit();
                finish();

            }
        }, 1000);

    }



    public void splashIntent(){

    }

}