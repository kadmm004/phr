package com.phr.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.phr.R;
import com.phr.home.NewregistActivity;

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

public class AddnewillsActivity extends AppCompatActivity {
    static String response;
    private ImageView iv_back_fill;
    private EditText et_date;
    private EditText et_hos;
    private EditText et_cause;
    private EditText et_detail;
    private RadioButton rb_first;
    private RadioButton rb_second;
    private Button btn_submit;
    public static final int SHOW_RESPONSE=1;
    public Handler handler=new Handler() {
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response=(String)msg.obj;
                    Toast.makeText(AddnewillsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewills);

        et_date=findViewById(R.id.et_date);
        et_hos=findViewById(R.id.et_hos);
        et_cause=findViewById(R.id.et_cause);
        et_detail=findViewById(R.id.et_detail);
        rb_first=findViewById(R.id.rb_first);
        rb_second=findViewById(R.id.rb_second);
        btn_submit=findViewById(R.id.btn_submit);
        iv_back_fill=findViewById(R.id.iv_back_fill);
        iv_back_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mrstate="初诊";
                String mrdate=et_date.getText().toString().trim();
                String hosid=et_hos.getText().toString().trim();
                String mrcause=et_cause.getText().toString().trim();
                String mrdetail=et_detail.getText().toString().trim();
                if(rb_first.isChecked()){
                    mrstate="初诊";
                }else if(rb_second.isChecked()){
                    mrstate="复诊";
                }

                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                String userid = sharedPreferences.getString("username", null);//(key,若无数据需要赋的值)
                System.out.println(userid);

                SendByHttpClient(userid,mrdate,hosid,mrcause,mrdetail,mrstate);
            }
        });
    }
    public void SendByHttpClient(final String userid, final String mrdate,final String hosid,final String mrcause,
                                 final String mrdetail,final String mrstate){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://192.168.1.3:8080/phr_server/MedicalRecordServlet");
                    List<NameValuePair> params=new ArrayList<NameValuePair>();

                    params.add(new BasicNameValuePair("USERID",userid));
                    params.add(new BasicNameValuePair("MRDATE",mrdate));
                    params.add(new BasicNameValuePair("HOSID",hosid));
                    params.add(new BasicNameValuePair("MRCAUSE",mrcause));
                    params.add(new BasicNameValuePair("MRDETAIL",mrdetail));
                    params.add(new BasicNameValuePair("MRSTATE",mrstate));

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