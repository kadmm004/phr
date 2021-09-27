package com.phr;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phr.fragment.AppointFragment;
import com.phr.fragment.HomePageFragment;
import com.phr.fragment.MinePageFragment;
import com.phr.fragment.WikiPageFragment;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainview;
    private LinearLayout bottom_guide;

    private LinearLayout linear_sy;
    private ImageView iv_sy;
    private TextView tv_sy;

    private LinearLayout linear_yy;
    private ImageView iv_register;
    private TextView tv_register;

    private LinearLayout linear_bk;
    private ImageView iv_info;
    private TextView tv_info;

    private LinearLayout linear_mine;
    private ImageView iv_mine;
    private TextView tv_mine;

    private AppointFragment appointPageFragment;
    private WikiPageFragment infoFragment;
    private HomePageFragment mainpageFragment;
    private MinePageFragment minepageFragment;

    private int tag=0;


    private void initView(){
        mainview=findViewById(R.id.mainview);
        bottom_guide=findViewById(R.id.bottom_guide);
        linear_sy=findViewById(R.id.linear_sy);
        iv_sy=findViewById(R.id.iv_sy);
        tv_sy=findViewById(R.id.tv_sy);
        linear_yy=findViewById(R.id.linear_yy);
        iv_register=findViewById(R.id.iv_register);
        tv_register=findViewById(R.id.tv_register);
        linear_bk=findViewById(R.id.linear_bk);
        iv_info=findViewById(R.id.iv_info);
        tv_info=findViewById(R.id.tv_info);
        linear_mine=findViewById(R.id.linear_mine);
        iv_mine=findViewById(R.id.iv_mine);
        tv_mine=findViewById(R.id.tv_mine);

        linear_sy.setOnClickListener(handler);
        linear_yy.setOnClickListener(handler);
        linear_bk.setOnClickListener(handler);
        linear_mine.setOnClickListener(handler);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        infoFragment=new WikiPageFragment();
        minepageFragment=new MinePageFragment();
        mainpageFragment=new HomePageFragment();
        appointPageFragment =new AppointFragment();
    }

    public void setSelectedStatus(int index){
        switch(index){
            case 0:
                iv_sy.setImageResource(R.drawable.mainpage_selected);
                tv_sy.setTextColor(Color.parseColor("#ed640f"));

                iv_register.setImageResource(R.drawable.registpage_logo);
                tv_register.setTextColor(Color.parseColor("#000000"));

                iv_info.setImageResource(R.drawable.infopage_logo);
                tv_info.setTextColor(Color.parseColor("#000000"));

                iv_mine.setImageResource(R.drawable.perpage_logo);
                tv_mine.setTextColor(Color.parseColor("#000000"));
            case 1:
                iv_sy.setImageResource(R.drawable.mainpage_logo);
                tv_sy.setTextColor(Color.parseColor("#000000"));

                iv_register.setImageResource(R.drawable.registpage_selected);
                tv_register.setTextColor(Color.parseColor("#ed640f"));

                iv_info.setImageResource(R.drawable.infopage_logo);
                tv_info.setTextColor(Color.parseColor("#000000"));

                iv_mine.setImageResource(R.drawable.perpage_logo);
                tv_mine.setTextColor(Color.parseColor("#000000"));
            case 2:
                iv_sy.setImageResource(R.drawable.mainpage_logo);
                tv_sy.setTextColor(Color.parseColor("#000000"));

                iv_register.setImageResource(R.drawable.registpage_logo);
                tv_register.setTextColor(Color.parseColor("#000000"));

                iv_info.setImageResource(R.drawable.infopage_selected);
                tv_info.setTextColor(Color.parseColor("#ed640f"));

                iv_mine.setImageResource(R.drawable.perpage_logo);
                tv_mine.setTextColor(Color.parseColor("#000000"));
            case 3:
                iv_sy.setImageResource(R.drawable.mainpage_logo);
                tv_sy.setTextColor(Color.parseColor("#000000"));

                iv_register.setImageResource(R.drawable.registpage_logo);
                tv_register.setTextColor(Color.parseColor("#000000"));

                iv_info.setImageResource(R.drawable.infopage_logo);
                tv_info.setTextColor(Color.parseColor("#000000"));

                iv_mine.setImageResource(R.drawable.perpage_selected);
                tv_mine.setTextColor(Color.parseColor("#ed640f"));
        }
    }


    View.OnClickListener handler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(tag==0){
                tag=1;
                switch(v.getId()){
                    case R.id.linear_sy:
                        getSupportFragmentManager().beginTransaction().add(R.id.mainview,mainpageFragment).commitAllowingStateLoss();
                        iv_sy.setImageResource(R.drawable.mainpage_selected);
                        tv_sy.setTextColor(Color.parseColor("#ed640f"));

                        iv_register.setImageResource(R.drawable.registpage_logo);
                        tv_register.setTextColor(Color.parseColor("#000000"));

                        iv_info.setImageResource(R.drawable.infopage_logo);
                        tv_info.setTextColor(Color.parseColor("#000000"));

                        iv_mine.setImageResource(R.drawable.perpage_logo);
                        tv_mine.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.linear_yy:
                        getSupportFragmentManager().beginTransaction().add(R.id.mainview, appointPageFragment).commitAllowingStateLoss();
                        iv_sy.setImageResource(R.drawable.mainpage_logo);
                        tv_sy.setTextColor(Color.parseColor("#000000"));

                        iv_register.setImageResource(R.drawable.registpage_selected);
                        tv_register.setTextColor(Color.parseColor("#ed640f"));

                        iv_info.setImageResource(R.drawable.infopage_logo);
                        tv_info.setTextColor(Color.parseColor("#000000"));

                        iv_mine.setImageResource(R.drawable.perpage_logo);
                        tv_mine.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.linear_bk:
                        getSupportFragmentManager().beginTransaction().add(R.id.mainview,infoFragment).commitAllowingStateLoss();
                        iv_sy.setImageResource(R.drawable.mainpage_logo);
                        tv_sy.setTextColor(Color.parseColor("#000000"));

                        iv_register.setImageResource(R.drawable.registpage_logo);
                        tv_register.setTextColor(Color.parseColor("#000000"));

                        iv_info.setImageResource(R.drawable.infopage_selected);
                        tv_info.setTextColor(Color.parseColor("#ed640f"));

                        iv_mine.setImageResource(R.drawable.perpage_logo);
                        tv_mine.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.linear_mine:
                        getSupportFragmentManager().beginTransaction().add(R.id.mainview,minepageFragment).commitAllowingStateLoss();
                        iv_sy.setImageResource(R.drawable.mainpage_logo);
                        tv_sy.setTextColor(Color.parseColor("#000000"));

                        iv_register.setImageResource(R.drawable.registpage_logo);
                        tv_register.setTextColor(Color.parseColor("#000000"));

                        iv_info.setImageResource(R.drawable.infopage_logo);
                        tv_info.setTextColor(Color.parseColor("#000000"));

                        iv_mine.setImageResource(R.drawable.perpage_selected);
                        tv_mine.setTextColor(Color.parseColor("#ed640f"));
                        break;
                }
            }
            else if(tag==1){
                switch(v.getId()){
                    case R.id.linear_sy:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainview,mainpageFragment).commitAllowingStateLoss();
                        iv_sy.setImageResource(R.drawable.mainpage_selected);
                        tv_sy.setTextColor(Color.parseColor("#ed640f"));

                        iv_register.setImageResource(R.drawable.registpage_logo);
                        tv_register.setTextColor(Color.parseColor("#000000"));

                        iv_info.setImageResource(R.drawable.infopage_logo);
                        tv_info.setTextColor(Color.parseColor("#000000"));

                        iv_mine.setImageResource(R.drawable.perpage_logo);
                        tv_mine.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.linear_yy:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainview, appointPageFragment).commitAllowingStateLoss();
                        iv_sy.setImageResource(R.drawable.mainpage_logo);
                        tv_sy.setTextColor(Color.parseColor("#000000"));

                        iv_register.setImageResource(R.drawable.registpage_selected);
                        tv_register.setTextColor(Color.parseColor("#ed640f"));

                        iv_info.setImageResource(R.drawable.infopage_logo);
                        tv_info.setTextColor(Color.parseColor("#000000"));

                        iv_mine.setImageResource(R.drawable.perpage_logo);
                        tv_mine.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.linear_bk:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainview,infoFragment).commitAllowingStateLoss();
                        iv_sy.setImageResource(R.drawable.mainpage_logo);
                        tv_sy.setTextColor(Color.parseColor("#000000"));

                        iv_register.setImageResource(R.drawable.registpage_logo);
                        tv_register.setTextColor(Color.parseColor("#000000"));

                        iv_info.setImageResource(R.drawable.infopage_selected);
                        tv_info.setTextColor(Color.parseColor("#ed640f"));

                        iv_mine.setImageResource(R.drawable.perpage_logo);
                        tv_mine.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.linear_mine:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainview,minepageFragment).commitAllowingStateLoss();
                        iv_sy.setImageResource(R.drawable.mainpage_logo);
                        tv_sy.setTextColor(Color.parseColor("#000000"));

                        iv_register.setImageResource(R.drawable.registpage_logo);
                        tv_register.setTextColor(Color.parseColor("#000000"));

                        iv_info.setImageResource(R.drawable.infopage_logo);
                        tv_info.setTextColor(Color.parseColor("#000000"));

                        iv_mine.setImageResource(R.drawable.perpage_selected);
                        tv_mine.setTextColor(Color.parseColor("#ed640f"));
                        break;
                }
            }
        }
    };


}