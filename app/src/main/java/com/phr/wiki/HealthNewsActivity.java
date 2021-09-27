package com.phr.wiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.phr.R;
import com.phr.adapter.NewsAdapter;
import com.phr.entity.News;

public class HealthNewsActivity extends AppCompatActivity {
    //json传递
    private String[] newsTitles = {"脑起搏器手术为帕金森患者带来新希望", "聚焦全病程管理 卵巢癌患者有了专业关爱中心"};
    private String[] newsContent = {"　  帕金森是中老年人常见的中枢神经系统慢性退行性运动障碍病，是目前仅次于脑卒中、阿尔兹海默症的严重威胁老年人群健康的第三大杀手。\n" +
            "    目前对于帕金森病的治疗主要分为药物治疗和手术治疗两大部分，大部分帕金森患者起初应用药物治疗后能够有效改善症状，而一旦疗效开始下降，就需要考虑脑深部电刺激手术（脑起搏器）治疗。\n" +
            "    脑深部电刺激手术俗称脑起搏治疗，就是利用脑立体定向手术，在患者脑内某一个特殊位置植入电极，通过安装在锁骨下的电刺激器，把电信号发送到大脑，借助外部遥控器，对脑内特定核团进行持续的电脉冲刺激，从而发挥刺激大脑的作用，以此来改善帕金森患者的症状。该手术长期效果良好，是目前公认的治疗帕金森病的有效治疗手段。\n" +
            "    首都医科大学三博脑科医院功能神经外科栾国明教授、关宇光主任近日成功完成ROSA ONE（第二代ROSA神经外科机器人）辅助下的帕金森病脑深部电刺激置入术手术，双侧电极植入精度均在0.3mm以内。目前，该患者（52岁的中年男性）已经开机程控，术前双手颤抖、肢体僵硬、走路不便的情况均有明显地改善。\n" +
            "    栾国明教授表示，ROSA机器人辅助下完成帕金森病的脑深部电刺激植入术，相比传统手术方式，最大优势在于电极可以近乎“无缝”植入到特定核团，最精准可以达到0.1mm，精准度相较之前提升了一个量级，这就从根本上保证了治疗效果。\n" ,
             "   卵巢癌是女性生殖系统肿瘤中死亡率最高的瘤种，全球5年生存率总体在40%左右，且多年未有提高。70%卵巢患者在诊断时已为晚期，初治后完全缓解中仍有70%-80%的患者会复发。中国抗癌协会妇科肿瘤专业委员会主任委员、重庆大学附属肿瘤医院妇瘤中心周琦教授强调，卵巢癌的确诊时分期晚、复发率高一直以来都是治疗难点，对于患者而言，多次复发造成严重的生理和心理负担，也对患者家庭造成沉重的经济负担，多方面的压力使患者难以坚持治疗。\n" +
                    "    近日，中国卵巢癌患者关爱中心项目在2019中国肿瘤学大会正式启动，该项目首批“卵巢癌患者关爱中心”落地全国数家重点医院，旨在帮助提高卵巢癌患者就医效率，推动患者完成从手术到化疗到维持治疗的全程治疗，协同医疗机构为卵巢癌患者提供更好的长期随访、管理和康复指导服务，从而降低患者复发率和死亡风险，提高患者生活质量。\n" +
                    "    项目由中国抗癌协会妇科肿瘤专业委员会、北京爱谱癌症患者关爱基金会、中国抗癌协会康复会联合发起。周琦教授介绍，“卵巢癌患者关爱中心”将聘请关爱使者协助医护人员管理卵巢癌患者从治疗到治疗后随访的全过程管理，通过开辟线上和医护人员关爱，鼓励卵巢癌患者坚持战胜疾病，合理治疗，改善预后。\n" +
                    "    北京爱谱癌症患者关爱基金会主席、中国抗癌协会康复会主任史安利教授表示，卵巢癌病情凶险，患者在长期治疗过程中会面临各种困惑，每一位卵巢癌患者的抗癌过程都是一场艰苦卓绝的长征，我们能做的就是最大程度给予患者支持，尤其是医疗资源的支持和心理方面的支持。\n"
                   };
    private List<News> newsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_news);
        ImageButton return_btn=findViewById(R.id.ib_returnIcon);
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //Intent it=new Intent();
               // it.setClass(HealthNewsActivity.this,BottomBarActivity.class);
               // startActivity(it);
            }
        });
        for (int i = 0; i < 2; i++) {//循环两遍，只添加一遍的话不足以充满屏幕
            News news = new News(newsTitles[i]);
            newsList.add(news);
        }
        NewsAdapter adapter=new NewsAdapter(HealthNewsActivity.this,
                R.layout.news_item, newsList);
        ListView listView = findViewById(R.id.lv_news);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = newsList.get(position);
                //Toast.makeText(HealthNewsActivity.this,news.getNewsTitle(),
                 //       Toast.LENGTH_LONG).show();
                Intent i = new Intent(HealthNewsActivity.this, HealthNewsInfoActivity.class);
                i.putExtra("newsTitleArray", newsTitles[position].toString());
                i.putExtra("newsContentArray", newsContent[position].toString());
                startActivity(i);
            }
        });
    }
}