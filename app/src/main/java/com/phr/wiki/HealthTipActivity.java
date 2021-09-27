package com.phr.wiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.phr.adapter.TipAdapter;
import com.phr.entity.Tip;

import java.util.ArrayList;
import java.util.List;
import com.phr.R;

public class HealthTipActivity extends AppCompatActivity {
    private String[] TipTitles = {"血脂异常意味着什么", "口臭是幽门螺杆菌引起的吗","在马桶上玩手机会增加痔疮发病率吗","如何进行宫颈疾病筛查？"};
    private List<Tip> tipList = new ArrayList<>();
    private String[] TipContent = {"   血脂异常意味着什么?胆固醇也有“好坏”之分吗?\n" +
            "一般来说，体检时的血脂检测包含总胆固醇" +
            "(TC)，甘油三酯(TG)，低密度脂蛋白胆固醇" +
            "(LDL-C)，高密度脂蛋白胆固醇(HDL-C)这四" +
            "项，其中任意一项不在正常范围内都会提示血" +
            "脂异常。\n" +
            "     而我们通常所说的“好”胆固醇指的是高密度脂" +
            "蛋白胆固醇，它可以预防多余的胆固醇在血管" +
            "内沉积。\n" +
            "     “坏”胆固醇指的是低密度脂蛋白胆固醇，它容" +
            "易沉积于心脑等部位血管的动脉壁内，逐渐形" +
            "成动脉粥样硬化性斑块，阻塞相应的血管，最" +
            "后可以引起冠心病、脑卒中等心脑血管疾病。",
            "幽门螺杆菌感染与口臭存在极强的相关性\n" +
                    "     口臭包括病理性口臭和生理性口臭两种，其中" +
                    "病理性口臭，又包括口源性口臭和非口源性口" +
                    "口源性顾名思义来自口腔，未治疗的龋齿、牙" +
                    "龈炎、牙周炎及口腔粘膜病等都可以引起口" +
                    "臭。\n" +
                    "     非口源性来自于某些疾病，例如急慢性胃炎、" +
                    "消化性溃疡出现酸臭味;幽门]梗阻、晚期胃癌" +
                    "常出现臭鸭蛋性口臭;糖尿病酮症酸中毒患者" +
                    "可呼出丙酮味气体，尿毒症患者呼出烂苹果气" +
                    "味。\n",
            "    现在很多人觉得，蹲马桶和手机大概是绝配，" +
                    "蹲厕所如果没有手机，心里就会慌慌的，觉得" +
                    "这次马桶白蹲了。如果因为玩手机增加了如厕" +
                    "的时间，那么也会增加痔疮的发病率。\n" +
                    "    因为长时间蹲坐在马桶上，会让腹部压力持续" +
                    "增加，使肛门直肠静脉里的血液回流不畅，造" +
                    "成直肠下端黏膜下和肛管皮肤下静脉丛淤血、" +
                    "曲张，形成柔软的静脉团一也就是 通常我们" +
                    "说的痔疮。\n" +
                    "    医生建议，排便时间最好控制在五分钟之前，" +
                    "不要养成排便时玩手机和看书的习惯，蹲便时" +
                    "间越久，淤血就会越严重，发生痔疮的可能性" +
                    "就越大。\n",
            "    癌症对人类的威胁越来越大，很多的癌症我们" +
                    "并没有研究清楚，但宫颈癌是人类目前研究得" +
                    "最清楚且最有把握能够控制的。\n" +
                    "    我们通常采用的是宫颈细胞学涂片(目前比较" +
                    "流行的是TCT)。 通过宫颈涂片，我们把宫颈" +
                    "上脱落的一些细胞，进行细胞学分析，就能知" +
                    "道它是否有癌前病变或癌变。如果TCT怀疑II" +
                    "级II级，医生就会进行阴道镜下活检检查。\n" +
                    "    很多人把阴道镜想象成是和胃镜、肠镜一样的" +
                    "有创性检查。其实阴道镜检查是一个非接触性" +
                    "检查，它是无痛的。宫颈是子宫的一部分，子" +
                    "宫本身也属于内脏器官，而人体的内脏器官大" +
                    "部分都是没有痛觉神经的。\n"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tip);
        ImageButton return_btn=findViewById(R.id.ib_returnIcon);
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent it=new Intent();
               // it.setClass(HealthTipActivity.this,BottomBarActivity.class);
              //  startActivity(it);
                finish();
            }
        });
        for (int i = 0; i < TipTitles.length; i++) {//循环两遍，只添加一遍的话不足以充满屏幕
            Tip tip = new Tip(TipTitles[i]);
            tipList.add(tip);
        }
        TipAdapter tipAdapter=new TipAdapter(HealthTipActivity.this,
                R.layout.health_tip_item, tipList);
        ListView tip_listView = findViewById(R.id.lv_healthtip);
        tip_listView.setAdapter(tipAdapter);
        tip_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tip tip  = tipList.get(position);
                Intent i = new Intent(HealthTipActivity.this, HealthTipInfoActivity.class);
                i.putExtra("tipTitleArray", TipTitles[position].toString());
                i.putExtra("tipContentArray", TipContent[position].toString());
                startActivity(i);
            }
        });
    }
}