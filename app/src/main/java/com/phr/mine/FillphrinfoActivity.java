package com.phr.mine;

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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

public class FillphrinfoActivity extends AppCompatActivity {
    private String response;

    private EditText et_height;
    private EditText et_weight;
    private EditText et_bmi;
    private EditText et_blood;
    private EditText et_waist;
    private Spinner sp_marriage;
    private Spinner sp_kid;
    private Spinner sp_smoke;
    private Spinner sp_drink;
    private Spinner sp_diet;
    private Spinner sp_allergy;
    private Spinner sp_exercise;
    private Button btn_save;
    private ImageView iv_back_fill;

    public static final int SHOW_RESPONSE=1;
    public Handler handler=new Handler() {
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response=(String)msg.obj;
                    Toast.makeText(FillphrinfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    public void init(){
        et_height=findViewById(R.id.et_height);
        et_weight=findViewById(R.id.et_weight);
        et_bmi=findViewById(R.id.et_bmi);
        et_blood=findViewById(R.id.et_blood);
        et_waist=findViewById(R.id.et_waist);
        sp_marriage=findViewById(R.id.sp_marriage);
        sp_kid=findViewById(R.id.sp_kid);
        sp_smoke=findViewById(R.id.sp_smoke);
        sp_drink=findViewById(R.id.sp_drink);
        sp_diet=findViewById(R.id.sp_diet);
        sp_allergy=findViewById(R.id.sp_allergy);
        sp_exercise=findViewById(R.id.sp_exercise);
        btn_save=findViewById(R.id.btn_save);
        iv_back_fill=findViewById(R.id.iv_back_fill);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillphrinfo);
        init();
        iv_back_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String height=et_height.getText().toString().trim();
                String weight=et_weight.getText().toString().trim();
                String blood=et_blood.getText().toString().trim();
                String waist=et_height.getText().toString().trim();
                String marriage=(String)sp_marriage.getSelectedItem();
                String kid=(String)sp_kid.getSelectedItem();
                String smoke=(String)sp_smoke.getSelectedItem();
                String drink=(String)sp_drink.getSelectedItem();
                String diet=(String)sp_diet.getSelectedItem();
                String allergy=(String)sp_allergy.getSelectedItem();
                String exercise=(String)sp_exercise.getSelectedItem();

                SendByHttpClient(height,weight,blood,waist,marriage,kid,smoke,drink,diet,allergy,exercise);

                String s=(String)response;
                String s1="true";
                if(s1.equals(s)){
                    Intent intent = new Intent();
                    intent.setClass(FillphrinfoActivity.this, MyphrdocActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void SendByHttpClient(final String height,final String weight,final String blood,
                                 final String waistline,final String marriage,final String born, final String exercise,
                                 final String diet,final String allergy,final String smoke, final String drink){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://192.168.1.3:8080/phr_server/EditphrServlet");
                    List<NameValuePair> params=new ArrayList<NameValuePair>();

                    SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                    String userid=sp.getString("username", null);

                    params.add(new BasicNameValuePair("UID",userid));
                    params.add(new BasicNameValuePair("UHEIGHT",height));
                    params.add(new BasicNameValuePair("UWEIGHT",weight));
                    params.add(new BasicNameValuePair("UBLOOD",blood));
                    params.add(new BasicNameValuePair("UWAISTLINE",waistline));
                    params.add(new BasicNameValuePair("UMARRIAGE",marriage));
                    params.add(new BasicNameValuePair("UBORN",born));
                    params.add(new BasicNameValuePair("UEXERCISE",exercise));
                    params.add(new BasicNameValuePair("UDIET",diet));
                    params.add(new BasicNameValuePair("UALLERGY",allergy));
                    params.add(new BasicNameValuePair("USMOKE",smoke));
                    params.add(new BasicNameValuePair("UDRINK",drink));

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

                        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                        StringBuffer sb = new StringBuffer();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        JSONArray ja = new JSONArray(sb.toString());
                        StringBuffer sb2 = new StringBuffer();
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jobj= (JSONObject)ja.get(i);
                            //sb2.append("用户名:").append(jobj.getString("id")).append(" ");
                            //sb2.append("密码:").append(jobj.getString("password")).append("\r\n");
                        }
                        reader.close();
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