package com.phr.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.phr.R;

public class HelptipsActivity extends AppCompatActivity {

    private ImageView iv_back_fill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helptips);

        iv_back_fill=findViewById(R.id.iv_back_fill);
        iv_back_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}