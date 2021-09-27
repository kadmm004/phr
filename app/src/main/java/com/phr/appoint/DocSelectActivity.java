package com.phr.appoint;
import android.widget.AdapterView.OnItemSelectedListener;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.phr.adapter.DoctorAdapter;
import com.phr.entity.Doctor;
import com.phr.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DocSelectActivity extends AppCompatActivity {

    StringBuffer sb;
    private List<Doctor> doctorList=new ArrayList<>();
    public int docID;

    String docname="docname";
    String depname="depname";
    String hosname="hosname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_select);
        ImageButton bt_return=(ImageButton)findViewById(R.id.ib_returnIcon);
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //标题头设置
        Intent intent=getIntent();
        depname=intent.getStringExtra("depname");
        hosname=intent.getStringExtra("hosname");

        TextView tv=(TextView)findViewById(R.id.tv_depname);
        tv.setText(depname);
        //医生列表
        final ListView listview=(ListView) findViewById(R.id.lv_doctors);

        GetDoctorFromHttpClient();

        DoctorAdapter adapter=new DoctorAdapter(DocSelectActivity.this,R.layout.item_doctor,doctorList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Doctor listItem = (Doctor) doctorList.get(position);
                Intent intent = new Intent(DocSelectActivity.this,ConfirmActivity.class);
                docname=listItem.getDocName();
                intent.putExtra("depname",depname);
                intent.putExtra("hosname",hosname);
                intent.putExtra("docname",docname);
                startActivity(intent);
            }
        });
    }


    protected void initDoctors() {

    }

    //返回至科室选择界面
    public void backToDep(View view) {
        finish();
        // Intent intent=new Intent();
        // intent.setClass(this,DeptSelectActivity.class);
        //  startActivity(intent);
    }


    public void GetDoctorFromHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("action","users"));
                String uri="http://192.168.1.3:8080/phr_server/DoctorServlet";
                HttpPost httpRequest = new HttpPost(uri);
                try {
                    HttpResponse response = httpclient.execute(httpRequest);
                    HttpEntity entity = response.getEntity();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    sb = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    reader.close();
                    JSONArray ja = new JSONArray(sb.toString());
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jobj= (JSONObject)ja.get(i);
                        String docname=jobj.getString("docname");
                        String doctitle=jobj.getString("doctitle");
                        String docnum=jobj.getString("docnum");
                        String docscore=jobj.getString("docscore");
                        String docmajor=jobj.getString("docmajor");
                        Doctor newDoc=new Doctor(docname,doctitle,docscore,docnum,docmajor);
                        newDoc.setDocImage(R.drawable.registpage_zhanwei);
                        doctorList.add(newDoc);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}