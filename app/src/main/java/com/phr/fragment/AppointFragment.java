package com.phr.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.phr.R;
import com.phr.adapter.HospitalAdapter;
import com.phr.appoint.DeptSelectActivity;
import com.phr.appoint.RegionSelectActivity;
import com.phr.entity.Hospital;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointFragment extends Fragment {

    StringBuffer sb;

    private List<Hospital> hospitalList=new ArrayList<>();
    //选择的医院名
    String hosname="上海交通大学医学院附属瑞金医院";
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;

    //医院排序
    private String[]sort_methods={"综合排序","离我最近","患者评价数"};
    private String[]hos_class={"三甲医院","三级医院","二级医院","一级医院","对外专科","其他","对外综合"};
    private String[]hos_type={"综合医院","眼科医院","儿童医院","妇产科医院","肿瘤医院","耳鼻咽喉医院","传染病医院"};
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String>adapter2;
    private ArrayAdapter<String>adapter3;

    public int sort_method=0;
    ImageButton button;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public AppointFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointFragment newInstance(String param1, String param2) {
        AppointFragment fragment = new AppointFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_appoint, container, false);
        //分享
        button = (ImageButton) view.findViewById(R.id.ib_shareIcon);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View contentView= LayoutInflater.from(getActivity())
                        .inflate(R.layout.activity_register,null);
                //声明弹出框
                final PopupWindow popupWindow=new PopupWindow();
                //为弹出框设定自定义布局
                popupWindow.setContentView(contentView);
                //显示对话框
                popupWindow.showAsDropDown(button);
            }
        });

        ImageButton search_btn=view.findViewById(R.id.ib_search);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                // intent.setClass(this,RegionSelectActivity.class);
                intent.setClass(getActivity(), RegionSelectActivity.class);
                startActivity(intent);
            }
        });

        //spinner
        //排序Spinner
        spinner1=(Spinner)view.findViewById(R.id.sp_sort);
        //设置下拉列表的风格
        adapter1=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,sort_methods);
        //添加adapter到spinner中
        spinner1.setAdapter(adapter1);
        //添加Spinner选择事件的监听
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
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

        //医院等级Spinner
        spinner2=(Spinner)view.findViewById(R.id.sp_hosclass);
        //设置下拉列表的风格
        adapter2=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,hos_class);
        //添加adapter到spinner中
        spinner2.setAdapter(adapter2);
        //添加Spinner选择事件的监听
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view,
                                       int i,
                                       long l) {
                //hos_class[i]即为选择项内容

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0){
            }
        });
        spinner2.setVisibility(View.VISIBLE);

        //医院类型Spinner
        spinner3=(Spinner)view.findViewById(R.id.sp_hostype);
        //设置下拉列表的风格
        adapter3=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,hos_type);
        //添加adapter到spinner中
        spinner3.setAdapter(adapter3);
        //添加Spinner选择事件的监听
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view,
                                       int i,
                                       long l) {
                //hos_type[i]即为选择项内容
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0){
            }
        });
        spinner3.setVisibility(View.VISIBLE);

        //listview的初始化
        ListView listview=view.findViewById(R.id.lv_hospitals);

        GetHospitalFromHttpClient();

        HospitalAdapter adapter=new HospitalAdapter(getActivity(), R.layout.item_hosinfo,hospitalList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hospital listItem = (Hospital) hospitalList.get(position);
                Intent intent = new Intent(getActivity(), DeptSelectActivity.class);
                hosname=listItem.getHosName();
                intent.putExtra("hosname",hosname);
                startActivity(intent);
            }
        });

        return view;
    }

    public void GetHospitalFromHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("action","users"));
                String uri="http://192.168.1.3:8080/phr_server/HospitalServlet";
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
                        String hosname=jobj.getString("hosname");
                        String hosdept=jobj.getString("hosdept");
                        String hosclass=jobj.getString("hosclass");
                        String hostype=jobj.getString("hostype");
                        String hoscomment=jobj.getString("hoscomment");
                        Hospital newHos=new Hospital(hosname,hosdept,hosclass,hostype,hoscomment);
                        newHos.setHosImage(R.drawable.registpage_zhanwei);
                        hospitalList.add(newHos);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}