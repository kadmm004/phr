package com.phr.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.phr.MainActivity;
import com.phr.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewregistActivity extends AppCompatActivity {
    static String response;

    private EditText et_idcard;
    private EditText et_tele;
    private EditText et_name;
    private EditText et_sex;
    private EditText et_addr;
    private EditText et_birth;
    private EditText et_nation;
    private EditText et_pwd;
    private EditText et_pwd2;
    private Button btn_submmit;
    private TextView tv_backtologin;

    public static final int SHOW_RESPONSE=1;
    public Handler handler=new Handler() {
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response=(String)msg.obj;
                    if(response.equals("true")){
                        Toast.makeText(NewregistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(NewregistActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(NewregistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newregist);

        et_idcard=findViewById(R.id.et_idcard);
        et_tele=findViewById(R.id.et_tele);
        et_name=findViewById(R.id.et_name);
        et_sex=findViewById(R.id.et_sex);
        et_addr=findViewById(R.id.et_addr);
        et_birth=findViewById(R.id.et_birth);
        et_nation=findViewById(R.id.et_nation);
        et_pwd=findViewById(R.id.et_pwd);
        et_pwd2=findViewById(R.id.et_pwd2);
        btn_submmit=findViewById(R.id.btn_submmit);
        tv_backtologin=findViewById(R.id.tv_backtologin);

        tv_backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idcard=et_idcard.getText().toString().trim();
                String tele=et_tele.getText().toString().trim();
                String name=et_name.getText().toString().trim();
                String sex=et_sex.getText().toString().trim();
                String addr=et_addr.getText().toString().trim();
                String birth=et_birth.getText().toString().trim();
                String nation=et_nation.getText().toString().trim();
                String pwd=et_pwd.getText().toString().trim();
                String pwd2=et_pwd2.getText().toString().trim();

                SendByHttpClient(idcard,tele,name,sex,addr,birth,nation, pwd,pwd2);




            }
        });



    }

    public void SendByHttpClient(final String idcard, final String tele,final String name,final String sex,
                                 final String addr,final String birth,final String nation,
                                 final String pwd,final String pwd2){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://192.168.1.3:8080/phr_server/RegistServlet");
                    List<NameValuePair> params=new ArrayList<NameValuePair>();

                    Date Birth=java.sql.Date.valueOf(birth);
                    Calendar c = Calendar.getInstance();
                    c.setTime(Birth);
                    int year = c.get(Calendar.YEAR);
                    int age=2020-year;

                    params.add(new BasicNameValuePair("IDCARD",idcard));
                    params.add(new BasicNameValuePair("TELE",tele));
                    params.add(new BasicNameValuePair("NAME",name));
                    params.add(new BasicNameValuePair("SEX",sex));
                    params.add(new BasicNameValuePair("ADDR",addr));
                    params.add(new BasicNameValuePair("BIRTH",birth));
                    params.add(new BasicNameValuePair("NATION",nation));
                    params.add(new BasicNameValuePair("PW",pwd));
                    params.add(new BasicNameValuePair("PW2",pwd2));

                    final UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params,"utf-8");
                    httpPost.setEntity(entity);
                    HttpResponse httpResponse= httpclient.execute(httpPost);
                    if(httpResponse.getStatusLine().getStatusCode()==200)
                    {
                        HttpEntity entity1=httpResponse.getEntity();
                        response= EntityUtils.toString(entity1, "utf-8");
                        Message message=new Message();
                        message.what=SHOW_RESPONSE;
                        message.obj=response;
                        handler.sendMessage(message);

                        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                        sp.edit()
                                .putString("username", tele)
                                .putString("password", pwd)
                                .putString("name",name)
                                .putInt("age",age)
                                .putString("sex",sex)
                                .apply();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.i("111111",e.toString());
                }
            }
        }).start();
    }
}