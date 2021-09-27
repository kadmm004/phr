package com.phr.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.phr.R;

public class AddphrdocActivity extends AppCompatActivity {
    private Button btn_submmit;
    private ImageView iv_back_fill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addphrdoc);

        iv_back_fill=findViewById(R.id.iv_back_fill);
        iv_back_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_submmit=findViewById(R.id.btn_submmit);
        btn_submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}