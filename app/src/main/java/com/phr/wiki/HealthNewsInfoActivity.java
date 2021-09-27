package com.phr.wiki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.phr.R;
import androidx.appcompat.app.AppCompatActivity;

public class HealthNewsInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_news_info);
        ImageButton return_btn=findViewById(R.id.ib_returnIcon);
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //Intent it=new Intent();
                //it.setClass(HealthNewsInfoActivity.this,HealthNewsActivity.class);
              //  startActivity(it);
            }
        });
        Bundle extras=getIntent().getExtras();
        String selected_item=extras.getString("newsTitleArray");
        TextView title = (TextView) findViewById (R.id.tv_title);
        title.setText(selected_item);
        String selected_item_content=extras.getString("newsContentArray");
        TextView content = (TextView) findViewById (R.id.tv_content);
        content.setText(selected_item_content);
    }
}