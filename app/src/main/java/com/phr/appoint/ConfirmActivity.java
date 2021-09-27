package com.phr.appoint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.phr.adapter.CommentAdapter;
import com.phr.adapter.TimeAdapter;
import com.phr.entity.Comment;
import com.phr.entity.Time;
import com.phr.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class ConfirmActivity extends AppCompatActivity {

    public String depname;
    public String docname;
    public String hosname;
    public String paname;
    private List<Time> timeList=new ArrayList<>();
    private ListView t_listview;
    private ListView c_listview;


    private List<Comment> commentList=new ArrayList<>();
    String time;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        //获取医生名字，获取科室名
        Intent intent=getIntent();
        depname=intent.getStringExtra("depname");
        docname=intent.getStringExtra("docname");
        hosname=intent.getStringExtra("hosname");
        TextView tv_docName=(TextView)findViewById(R.id.tv_confirm_docName);
        TextView tv_depName=(TextView)findViewById(R.id.tv_confirm_depname);
        TextView tv_hosName=(TextView)findViewById(R.id.tv_confirm_hosname);
        tv_docName.setText(docname);
        tv_depName.setText(depname);
        tv_hosName.setText(hosname);

        //时间列表
        t_listview=(ListView) findViewById(R.id.lv_regist_info);
        initTimes();
        TimeAdapter adapter0=new TimeAdapter(ConfirmActivity.this,R.layout.item_time,timeList);
        t_listview.setAdapter(adapter0);

        //评价listview
        //评价列表
        c_listview=(ListView) findViewById(R.id.lv_comments);
        initComments();
        CommentAdapter adapter=new CommentAdapter(this,R.layout.item_comment,commentList);
        c_listview.setAdapter(adapter);

        ListView lv_rinfo=(ListView)findViewById(R.id.lv_regist_info);
        lv_rinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Time info=timeList.get(i);
                time=info.getRegistTime();
                price=info.getRegistPrice();
                Dialog alertDialog=new AlertDialog.Builder(ConfirmActivity.this)
                        .setTitle("挂号确认")
                        .setMessage("您的选择的挂号时间为:\n"+time+"\n金额："+price)
                        //.setIcon(R.drawable.ic_launcher_foreground)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ConfirmActivity.this,"预约成功",Toast.LENGTH_SHORT).show();
                                //数据库写入
                                insertIntoDB();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ConfirmActivity.this,"取消成功",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

    }

    protected void initComments(){
        Comment newCom=new Comment();
        newCom.setPatientName("王**");
        newCom.setDisease("闭塞性血栓性脉管炎");
        newCom.setDate("2020年7月4日");
        newCom.setCost("666元");
        newCom.setMethod("药物");
        newCom.setComment("     很好，能详细地解答病人的疑问");
        newCom.setResult("有效果");
        commentList.add(newCom);
        Comment newCom1=new Comment();
        newCom1.setPatientName("赵**");
        newCom1.setDisease("过敏性鼻炎");
        newCom1.setDate("2020年7月8日");
        newCom1.setCost("250元");
        newCom1.setMethod("药物");
        newCom1.setComment("     很好，医生技术很好！");
        newCom1.setResult("非常有效");
        commentList.add(newCom1);
        Comment newCom2=new Comment();
        newCom2.setPatientName("李**");
        newCom2.setDisease("糖尿病");
        newCom2.setDate("2020年7月11日");
        newCom2.setCost("50元");
        newCom2.setMethod("药物");
        newCom2.setComment("     实话实说，有给合理建议");
        newCom2.setResult("有待观察");
        commentList.add(newCom);
    }

    protected void initTimes() {
        Time newTime=new Time();
        newTime.setRegistPrice("50元");
        newTime.setRegistTime("2020年7月10日 上午  ");
        timeList.add(newTime);
        Time newTime1=new Time();
        newTime1.setRegistPrice("80元");
        newTime1.setRegistTime("2020年7月11日 下午  ");
        timeList.add(newTime1);
        Time newTime2=new Time();
        newTime2.setRegistPrice("80元");
        newTime2.setRegistTime("2020年7月12日 上午  ");
        timeList.add(newTime2);
    }

    public void insertIntoDB(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                List<NameValuePair> params=new ArrayList<NameValuePair>();

                SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                paname=sp.getString("uname", null);

                params.add(new BasicNameValuePair("action","users"));
                params.add(new BasicNameValuePair("paname",paname));
                params.add(new BasicNameValuePair("depname",depname));
                params.add(new BasicNameValuePair("docname",docname));
                params.add(new BasicNameValuePair("price",price));
                params.add(new BasicNameValuePair("time",time));
                String uri="http://192.168.1.3:8080/phr_server/AppointServlet";
                HttpPost httpRequest = new HttpPost(uri);
                try {
                    httpclient.execute(httpRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void backToDoc(View view) {
        finish();
    }
}