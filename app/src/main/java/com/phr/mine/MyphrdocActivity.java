package com.phr.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phr.R;

public class MyphrdocActivity extends AppCompatActivity {

    private ImageView iv_back_first;
    private TextView tv_tomyills;
    private TextView tv_tophrdoc;

    private TextView phrdoc_name;
    private TextView phrdoc_agesex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myphrdoc);

        phrdoc_name=findViewById(R.id.phrdoc_name);
        phrdoc_agesex=findViewById(R.id.phrdoc_agesex);
        //参数初始化
        SharedPreferences sp = getSharedPreferences("login",Context.MODE_PRIVATE);
        phrdoc_name.setText(sp.getString("uname", null));
        String agesex=sp.getString("uage",null)+"岁  "+sp.getString("usex",null);
        phrdoc_agesex.setText(agesex);


        //完善健康档案
        tv_tophrdoc=findViewById(R.id.tv_tophrdoc);
        tv_tophrdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(MyphrdocActivity.this, FillphrinfoActivity.class);
                startActivity(it);
            }
        });


        //后退，回到上一级
        iv_back_first=findViewById(R.id.iv_back_first);
        iv_back_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //新建病历
        tv_tomyills=findViewById(R.id.tv_tomyills);
        tv_tomyills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(MyphrdocActivity.this, AddnewillsActivity.class);
                startActivity(it);
            }
        });

    }
}