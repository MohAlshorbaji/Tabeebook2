package com.example.tabeebook.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tabeebook.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME = 3000; //This is 3 seconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                          startActivity(intent);

                                          finish();
                                      }
                                  },
                SPLASH_TIME);

    }
}