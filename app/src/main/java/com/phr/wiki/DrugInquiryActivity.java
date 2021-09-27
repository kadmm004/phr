package com.phr.wiki;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.phr.adapter.DrugAdapter;
import com.phr.entity.Drug;
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

public class DrugInquiryActivity extends AppCompatActivity {
    StringBuffer sb;
    public static final int SHOW_RESPONSE=1;
    DrugAdapter adapter1;

    private List<Drug> drugList = new ArrayList<>();
    private SimpleCursorAdapter adapter;//适配器
    private Cursor cursor;//记录集游标
    private EditText searchText;//搜索栏
    private String condition;//查询条件

    public Handler handler=new Handler() {
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case SHOW_RESPONSE:
                    DrugAdapter adapter=new DrugAdapter(DrugInquiryActivity.this,
                            R.layout.drug_item, drugList);
                    ListView listView = findViewById(R.id.lv_durg);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Drug drug = drugList.get(position);
                            Intent i = new Intent(DrugInquiryActivity.this, DrugInfoActivity.class);
                            i.putExtra("drugNameArray",drugList.get(position).getDrugname());
                            i.putExtra("vertinumArray",drugList.get(position).getVertinum());
                            i.putExtra("ingredientArray",drugList.get(position).getIngredient());
                            i.putExtra("manifuncArray",drugList.get(position).getMainfunc());
                            i.putExtra("dosageArray",drugList.get(position).getDosage());
                            i.putExtra("tatooArray",drugList.get(position).getTaboo());
                            i.putExtra("adverseactionArray",drugList.get(position).getAdverseaction());
                            i.putExtra("attentionArray",drugList.get(position).getAttention());
                            i.putExtra("standardsArray",drugList.get(position).getStandards());
                            i.putExtra("productorArray",drugList.get(position).getProductor());
                            i.putExtra("categoryArray",drugList.get(position).getCategory());
                            i.putExtra("priceArray",drugList.get(position).getPrice());
                            startActivity(i);
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_inquiry);
        ImageButton return_btn = findViewById(R.id.ib_returnIcon);
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        GetDrugFromHttpClient();
        searchText = (EditText)findViewById(R.id.ev_drug_search);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                condition = searchText.getText().toString();
                List<Drug> tempList = new ArrayList<>();
                int length=drugList.size();
                for(int i=0;i<length;++i){
                    if(drugList.get(i).getDrugname().contains(condition)){
                        tempList.add(drugList.get(i));
                    }
                }
                adapter1=new DrugAdapter(DrugInquiryActivity.this,
                        R.layout.drug_item, tempList);
                ListView listView = findViewById(R.id.lv_durg);
                listView.setAdapter(adapter1);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Drug drug = drugList.get(position);
                        Intent i = new Intent(DrugInquiryActivity.this, DrugInfoActivity.class);
                        i.putExtra("drugNameArray",drugList.get(position).getDrugname());
                        i.putExtra("vertinumArray",drugList.get(position).getVertinum());
                        i.putExtra("ingredientArray",drugList.get(position).getIngredient());
                        i.putExtra("manifuncArray",drugList.get(position).getMainfunc());
                        i.putExtra("dosageArray",drugList.get(position).getDosage());
                        i.putExtra("tatooArray",drugList.get(position).getTaboo());
                        i.putExtra("adverseactionArray",drugList.get(position).getAdverseaction());
                        i.putExtra("attentionArray",drugList.get(position).getAttention());
                        i.putExtra("standardsArray",drugList.get(position).getStandards());
                        i.putExtra("productorArray",drugList.get(position).getProductor());
                        i.putExtra("categoryArray",drugList.get(position).getCategory());
                        i.putExtra("priceArray",drugList.get(position).getPrice());
                        startActivity(i);
                    }
                });
            }
        });
    }

    public void GetDrugFromHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("action","users"));
                String uri="http://192.168.1.3:8080/phr_server/DrugServlet";
                HttpPost httpReqest = new HttpPost(uri);
                try {
                    HttpResponse response = httpclient.execute(httpReqest);
                    HttpEntity entity = response.getEntity();
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
                        JSONObject jobj= (JSONObject)ja.get(i);
                        String drugid=jobj.getString("drugid");
                        String drugname=jobj.getString("drugname");
                        String vertinum=jobj.getString("vertinum");
                        String ingredient=jobj.getString("ingredient");
                        String mainfunc=jobj.getString("mainfunc");
                        String dosage=jobj.getString("dosage");
                        String adverseaction=jobj.getString("adverseaction");
                        String taboo=jobj.getString("taboo");
                        String attention=jobj.getString("attention");
                        String standards=jobj.getString("standards");
                        String productor=jobj.getString("productor");
                        String category=jobj.getString("category");
                        double price=jobj.getDouble("price");
                        Drug drug=new Drug(drugid,drugname,vertinum,ingredient,mainfunc,dosage,adverseaction,taboo,attention,standards,productor,category,price);
                        drugList.add(drug);
                    }
                } catch (Exception e) {
                    Log.i("111111", sb.toString());
                    Log.i("222222", e.toString());
                }
            }
        }).start();
        Message message=new Message();
        message.what=SHOW_RESPONSE;
        handler.sendMessage(message);
    }
}