package com.phr.entity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.phr.R;
import com.phr.adapter.HospitalAdapter;

public class TestActivity extends AppCompatActivity {

    private List<Hospital> hospitalList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ListView listview=(ListView) findViewById(R.id.lv_hosinfo);
        initHospitals();
        HospitalAdapter adapter=new HospitalAdapter(this,R.layout.item_hosinfo,hospitalList);
        listview.setAdapter(adapter);
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