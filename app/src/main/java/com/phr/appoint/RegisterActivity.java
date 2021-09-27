package com.phr.appoint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.phr.adapter.HospitalAdapter;
import com.phr.entity.Hospital;

import java.util.ArrayList;
import java.util.List;

import com.phr.R;

public class RegisterActivity extends Activity {
    private ImageView ib_returnIcon;

    private List<Hospital> hospitalList=new ArrayList<>();
    //选择的医院名
    String hosname="上海交通大学医学院附属瑞金医院";

    //医院排序
    private String[]sort_methods={"综合排序","离我最近","患者评价数"};
    private ArrayAdapter<String>adapter;
    public int sort_method=0;
    ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ib_returnIcon=findViewById(R.id.ib_returnIcon);
        ib_returnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //分享
        button = (ImageButton) findViewById(R.id.ib_shareIcon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopupWindow();
            }
        });

        //排序Spinner
        Spinner spinner1=(Spinner)findViewById(R.id.sp_sort);
        //设置下拉列表的风格
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,sort_methods);
        //添加adapter到spinner中
        spinner1.setAdapter(adapter);
        //添加Spinner选择事件的监听
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view,
                                       int i,
                                       long l) {
                //sort_methods[i]即为选择项内容
                sort_method=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0){
            }
        });
        spinner1.setVisibility(View.VISIBLE);

        //listview的初始化
        final ListView listview=(ListView) findViewById(R.id.lv_hospitals);
        initHospitals();
        HospitalAdapter adapter=new HospitalAdapter(this,R.layout.item_hosinfo,hospitalList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Hospital listItem = (Hospital) listview.getItemAtPosition(position);
                  hosname=listItem.getHosName();
            }
        });

    }

    protected void initPopupWindow(){
        //加载popupWindow的布局文件
        View contentView= LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.activity_register,null);
        //声明弹出框
        final PopupWindow popupWindow=new PopupWindow();
        //为弹出框设定自定义布局
        popupWindow.setContentView(contentView);
        //显示对话框
        popupWindow.showAsDropDown(button);

    }

    public void regionSelect(View view) {
        Intent intent=new Intent();
        intent.setClass(this,RegionSelectActivity.class);
        startActivity(intent);
    }


    public void GOTOdept(View view) {
        Intent intent=new Intent();
        intent.putExtra("hosname",hosname);
        intent.setClass(this,DeptSelectActivity.class);
        startActivity(intent);
    }

    //初始化医院数据
    private void initHospitals(){
        Hospital newHos1=new Hospital();
        newHos1.setHosImage(R.drawable.registpage_zhanwei);
        newHos1.setHosName("上海交通大学医学院附属瑞金医院");
        newHos1.setHosClass("三级甲等");
        newHos1.setHosType("综合医院");
        newHos1.setHosDept("泌尿外科门诊，神经外科，普外科");
        newHos1.setHosComment("全国综合患者好评排行第4名");
        hospitalList.add(newHos1);

        Hospital newHos2=new Hospital();
        newHos2.setHosImage(R.drawable.registpage_zhanwei);
        newHos2.setHosName("空军军医大学西京医院");
        newHos2.setHosClass("三级甲等");
        newHos2.setHosType("综合医院");
        newHos2.setHosDept("泌尿外科门诊，神经外科，普外科");
        newHos2.setHosComment("全国综合患者好评排行第1名");
        hospitalList.add(newHos2);

    }
}