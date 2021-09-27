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
import android.widget.Toast;

import com.phr.MainActivity;
import com.phr.R;
import com.phr.mine.HelptipsActivity;

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
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private String response;
    private EditText et_loginname;
    private EditText et_pwd;
    private Button btn_login;
    private Button btn_register;

    StringBuffer sb;
    String uid;
    String uidcard;
    String usex;
    String ubirth;
    String uage;
    String uaddr;
    String utele;
    String uloginname;
    String type;
    String uname;

    public static final int SHOW_RESPONSE=1;
    public Handler handler=new Handler() {
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response=(String)msg.obj;
                    String s="true";
                    if(s.equals(type)){
                        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                        sp.edit()
                                .putString("username", utele)
                                .putString("uname", uname)
                                .putString("uage", uage)
                                .putString("usex", usex)
                                .apply();
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, type, Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_loginname=findViewById(R.id.et_loginname);
        et_pwd=findViewById(R.id.et_pwd);
        btn_login=findViewById(R.id.btn_login);
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, NewregistActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=et_loginname.getText().toString().trim();
                String upwd=et_pwd.getText().toString().trim();
                SendByHttpClient(uname,upwd);
            }
        });
    }

    public void SendByHttpClient(final String id, final String pw){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://192.168.1.3:8080/phr_server/LoginServlet");
                    List<NameValuePair> params=new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("ID",id));
                    params.add(new BasicNameValuePair("PW",pw));
                    httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
                    HttpResponse httpResponse= httpclient.execute(httpPost);
                    HttpEntity entity=httpResponse.getEntity();
                    if(entity!=null&&httpResponse.getStatusLine().getStatusCode()<400) {
                        Message message=new Message();
                        message.what=SHOW_RESPONSE;
                        message.obj=type;
                        handler.sendMessage(message);

                        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                        sb = new StringBuffer();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        reader.close();
                        JSONArray ja = new JSONArray(sb.toString());
                        StringBuffer sb2 = new StringBuffer();
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jobj = (JSONObject) ja.get(i);
                            uid = jobj.getString("uid");
                            uname = jobj.getString("uname");
                            uidcard = jobj.getString("uidcard");
                            usex = jobj.getString("usex");
                            ubirth = jobj.getString("ubirth");
                            uage = jobj.getString("uage");
                            uaddr = jobj.getString("uaddr");
                            utele = jobj.getString("utele");
                            uloginname = jobj.getString("uloginname");
                            type = jobj.getString("type");
                        }
                    }
                } catch (Exception e) {
                    Log.i("111111", sb.toString());
                    Log.i("222222", e.toString());
                }
            }
        }).start();
    }
}