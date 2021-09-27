package com.phr.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.phr.MainActivity;
import com.phr.R;

public class WelcomeActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH=2000; //延迟3秒
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                    WelcomeActivity.this.startActivity(mainIntent);
                    WelcomeActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }



}