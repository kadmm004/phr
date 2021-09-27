package com.phr.appoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.phr.R;

public class RegionSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_select);
    }

    public void closePage(View view) {
        finish();
        //Intent intent = new Intent();
        //intent.setClass(this,RegisterActivity.class);
       // startActivity(intent);
    }

    public void onClick()
    {

    }
}