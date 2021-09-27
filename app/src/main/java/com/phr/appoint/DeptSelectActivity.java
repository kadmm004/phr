package com.phr.appoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.phr.R;

import androidx.appcompat.app.AppCompatActivity;

public class DeptSelectActivity extends AppCompatActivity {

    public int depID;
    public String hosname;
    public String depname;
    TextView tv_ks;  //选中的科室
    String department;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_select);

        //获取上一个页面传来的医院名
        Intent intent=getIntent();
        hosname=intent.getStringExtra("hosname");
        TextView tv_hosname=(TextView)findViewById(R.id.tv_dep_hosname);
        tv_hosname.setText(hosname);

        //spinner显示科室信息
        final ListView lv_dep=(ListView)findViewById(R.id.lv_select_dep);
        this.registerForContextMenu(lv_dep);
        final String[] dep=new String[]{"推荐科室","内科","外科","骨科","妇产科","儿科","皮肤性病科",
                "男科","眼科","耳鼻咽喉头颈科","口腔科","肿瘤科","中医科","不孕不育科","精神科",
                "疼痛科","康复医学科","医学影像科","病理科","护理门诊","营养科","其他"};
        lv_dep.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dep));

        lv_dep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String listItem = (String) dep[position];
                department = listItem;
                lv_dep.showContextMenu();
            }
        });

        lv_dep.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener(){
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.setHeaderTitle(department);
                if(department=="推荐科室"){
                    menu.add(0,1,0,"门诊内分泌");
                    menu.add(0,2,0,"门诊肾脏");
                    menu.add(0,3,0,"门诊血液");
                    menu.add(0,4,0,"门诊普外");
                    menu.add(0,5,0,"门诊消化");
                    menu.add(0,6,0,"门诊高血压");
                    menu.add(0,7,0,"门诊妇科");
                    menu.add(0,8,0,"门诊心脏");
                }
                else if(department=="内科"){
                    menu.add(0,1,0,"门诊心脏");
                    menu.add(0,2,0,"门诊高血压");
                    menu.add(0,3,0,"门诊呼吸科");
                    menu.add(0,4,0,"门诊消化");
                    menu.add(0,5,0,"门诊神内");
                    menu.add(0,6,0,"门诊肾脏");
                    menu.add(0,7,0,"门诊血液");
                    menu.add(0,8,0,"门诊风湿免疫");
                    menu.add(0,9,0,"老年科");
                    menu.add(0,10,0,"门诊感染病");
                    menu.add(0,11,0,"门诊内分泌");
                }
                else if(department=="外科"){
                    menu.add(0,1,0,"门诊普外");
                    menu.add(0,2,0,"门诊胸外");
                    menu.add(0,3,0,"心外科");
                    menu.add(0,4,0,"功能神外");
                    menu.add(0,5,0,"神经外科");
                    menu.add(0,6,0,"泌尿外科门诊");
                    menu.add(0,7,0,"门诊乳腺疾病诊治中心");
                    menu.add(0,8,0,"门诊烧伤整形");
                }
                else if(department=="骨科"){
                    menu.add(0,1,0,"门诊骨科");
                    menu.add(0,2,0,"门诊伤科");
                }
                else if(department=="妇科"){
                    menu.add(0,1,0,"门诊妇科");
                }
                else if(department=="儿科"){
                    menu.add(0,1,0,"门诊儿内");
                    menu.add(0,2,0,"门诊儿外");
                }
                else if(department=="皮肤性病科"){
                    menu.add(0,1,0,"门诊皮肤科");
                }
                else if(department=="男科"){
                    menu.add(0,1,0,"妇产科生殖医学中心门");
                }
                else if(department=="眼科"){
                    menu.add(0,1,0,"门诊眼科");
                }
                else if(department=="耳鼻咽喉头颈科"){
                    menu.add(0,1,0,"耳鼻喉科");
                }
                else if(department=="口腔科"){
                    menu.add(0,1,0,"口腔科");
                }
                else if(department=="肿瘤科"){
                    menu.add(0,1,0,"肿瘤科");
                    menu.add(0,2,0,"肿瘤科门诊");
                }
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        depname=item.toString();
        gotoDOC();
        return true;
    }


    public void gotoDOC(){
        Intent intent = new Intent(DeptSelectActivity.this, DocSelectActivity.class);
        intent.putExtra("depname", depname);
        intent.putExtra("hosname", hosname);
        startActivity(intent);
    }


    public void backToRegist(View view) {
        finish();
        // Intent intent=new Intent();
        //intent.setClass(this,RegisterActivity.class);
        // startActivity(intent);
    }

    //点击左侧科室名，列出右侧详细科室列表
    public void listDept(View view) {

    }

    //跳转至选择医生，时间界面
    public void GOTOdoc(View view) {
        Intent intent=new Intent(this,DocSelectActivity.class);
        //获取当前选中项的id（textview的id）
        depID=view.getId();
        //获取当前选中的textview（科室）
        TextView tv=(TextView)findViewById(depID);
        //科室名
        depname=(String)tv.getText();

        intent.putExtra("depname",depname);
        intent.putExtra("hosname",hosname);
        startActivity(intent);
    }
}