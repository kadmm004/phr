package com.phr.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phr.R;
import com.phr.mine.AddphrdocActivity;
import com.phr.mine.FillperinfoActivity;
import com.phr.mine.HelptipsActivity;
import com.phr.mine.MyphrdocActivity;
import com.phr.mine.SettingsActivity;
import com.phr.wiki.HealthNewsActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MinePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MinePageFragment extends Fragment {

    private TextView tv_tofill;
    private TextView tv_toadddoc;
    private LinearLayout phrdoc_exist;
    private Button btn_mysettings;
    private Button btn_myhelp;

    private TextView phrdoc_name;
    private TextView phrdoc_agesex;
    private ImageView iv_re;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MinePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MinePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MinePageFragment newInstance(String param1, String param2) {
        MinePageFragment fragment = new MinePageFragment();
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
        View view=inflater.inflate(R.layout.fragment_mine_page, container, false);

        phrdoc_name=view.findViewById(R.id.phrdoc_name);
        phrdoc_agesex=view.findViewById(R.id.phrdoc_agesex);
        iv_re=view.findViewById(R.id.iv_re);
        iv_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        SharedPreferences sp = getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        phrdoc_name.setText(sp.getString("uname", null));
        String agesex=sp.getString("uage",null)+"岁  "+sp.getString("usex",null);
        phrdoc_agesex.setText(agesex);

        //完善个人信息
        tv_tofill=view.findViewById(R.id.tv_tofill);
        tv_tofill.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(getActivity(), FillperinfoActivity.class);
                startActivity(it);
            }
        });

        //已有健康档案
        phrdoc_exist=view.findViewById(R.id.phrdoc_exist);
        phrdoc_exist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(getActivity(), MyphrdocActivity.class);
                startActivity(it);
            }
        });

        //添加健康档案
        tv_toadddoc=view.findViewById(R.id.tv_toadddoc);
        tv_toadddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(getActivity(), AddphrdocActivity.class);
                startActivity(it);
            }
        });

        //工具和服务-设置
        btn_mysettings=view.findViewById(R.id.btn_mysettings);
        btn_mysettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(getActivity(), SettingsActivity.class);
                startActivity(it);
            }
        });

        //工具和服务-帮助
        btn_myhelp=view.findViewById(R.id.btnmyhelp);
        btn_myhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(getActivity(), HelptipsActivity.class);
                startActivity(it);
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}