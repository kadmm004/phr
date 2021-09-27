package com.phr.wiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.phr.R;

public class HealthTipInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tip_info);
        ImageButton return_btn=findViewById(R.id.ib_returnIcon);
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent it=new Intent();
                finish();
                //it.setClass(HealthTipInfoActivity.this,HealthTipActivity.class);
                //startActivity(it);
            }
        });
        Bundle extras=getIntent().getExtras();
        String selected_item=extras.getString("tipTitleArray");
        TextView title = (TextView) findViewById (R.id.tv_title);
        title.setText(selected_item);
        String selected_item_content=extras.getString("tipContentArray");
        TextView content = (TextView) findViewById (R.id.tv_content);
        content.setText(selected_item_content);
    }
}