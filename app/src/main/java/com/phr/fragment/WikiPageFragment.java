package com.phr.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import com.phr.R;
import com.phr.adapter.HealthStationInfoAdapter;
import com.phr.adapter.TipAdapter;
import com.phr.entity.HealthStationInfo;
import com.phr.entity.Tip;
import com.phr.wiki.DrugInquiryActivity;
import com.phr.wiki.HealthNewsActivity;
import com.phr.wiki.HealthTipActivity;
import com.phr.wiki.HealthTipInfoActivity;
import com.phr.wiki.HealthstationActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WikiPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WikiPageFragment extends Fragment {
    private String[] healthStationInfoTitles = {"夏季如何调理肠胃？“三戒”助消化", "慢性病防治基本知识"};
    private String[] healthStationInfoContent = {
            "    盛夏已至，天气越来越炎热，胃肠道疾病也到了高发季。如何才能有效地调整胃肠功能，预防胃肠疾病？今天就和大家说说“三戒”。\n\n"+
                    "【一戒过食生冷】\n"+
                    "    夏季，在气温升高的同进，大自然的阳气日渐旺盛，自然界的湿度也随之增加。相应的，人体内的湿气也会相应增加。人们往往食欲不振，总喜欢吃点清凉爽口的东西，各种冷饮、凉食、凉菜便大行其道。虽然凉食爽口，但过食则损伤脾胃阳气，加重体内的湿气，食欲更差，同时还可能出现胃脘胀闷，大便稀溏等症状。\n\n"+
                    "【二戒过食辛辣】\n"+
                    "     辛辣之物有化湿通气的作用，可以增加食欲。夏天阳气旺盛，阳气性动上行而散。有的人天气一热便觉得身软乏力，多汗，精神不振，便是因为阳气过盛，汗出过多，致气阴两伤。如果再过食辛辣，辛能散气，热能伤阴，会加重人体气阴的损伤。虽然有爽口之效，但却埋下了耗气伤阴的隐患。\n\n"+
                    "【三戒多食暴饮】\n"+
                    "天气炎热，脾胃湿气渐盛，湿困脾胃，导致食欲不振，汗出过多，又可损伤气阴，所以人体胃肠的消化功能明显下降。因为天气炎热，人们喜欢边吃宵夜边纳凉，无形之中便会多食暴饮。胃肠功能本就下降，再加多食暴饮，则胃肠的负担更重，容易导致胃肠功能紊乱。\n\n",
            "家族性高发的慢性病还能预防吗？\n"
                    +"     高血压、糖尿病、血脂异常、肥胖、冠心病、脑卒中和肿瘤均为多基因遗传病，同时受环境和心理因素的影响。遗传因素与环境因素作用的总和决定一个人是否易于患病，即易患性。这种易患性高到一定的程度（超过阈值）时才会发病。显然，为预防发病，疾病的遗传度越高，就越应该注意控制环境和心理因素的影响，以防止其易患性达到发病的阈值。那种只讲遗传，忽视可改变危险因素干预的宿命论观点是完全错误的。事实上，80%以上的心脏病、脑卒中和糖尿病，40%以上的肿瘤都是可以预防的。\n\n"
                    +"预防慢性病需要何时开始？何时结束？\n"
                    +"     慢性病是众多危险因素长期作用的结果。很多不健康的生活方式在儿童时期既已养成，甚至一些慢性病在儿童时期就已开始萌芽，动脉硬化的早期病变脂质条纹在青少年中就可以检出，因此预防慢性病越早越好。另外，一些儿童已经患上了儿童慢性病，除肥胖外，高血压、糖尿病在儿童中已不少见。因此，慢性病预防既要早开始，还要终其一生，等岁数大了再去预防往往“木已成舟”了。\n\n"
                    +"\n\n"};
    private String[] TipTitles = {"血脂异常意味着什么", "口臭是幽门螺杆菌引起的吗","在马桶上玩手机会增加痔疮发病率吗","如何进行宫颈疾病筛查？"};
    private String[] TipContent = {"    血脂异常意味着什么?胆固醇也有“好坏”之分吗?\n\n" +
            "     一般来说，体检时的血脂检测包含总胆固醇" +
            "(TC)，甘油三酯(TG)，低密度脂蛋白胆固醇" +
            "(LDL-C)，高密度脂蛋白胆固醇(HDL-C)这四" +
            "项，其中任意一项不在正常范围内都会提示血" +
            "脂异常。\n\n" +
            "     而我们通常所说的“好”胆固醇指的是高密度脂" +
            "蛋白胆固醇，它可以预防多余的胆固醇在血管" +
            "内沉积。\n\n" +
            "     “坏”胆固醇指的是低密度脂蛋白胆固醇，它容" +
            "易沉积于心脑等部位血管的动脉壁内，逐渐形" +
            "成动脉粥样硬化性斑块，阻塞相应的血管，最" +
            "后可以引起冠心病、脑卒中等心脑血管疾病。",
            "幽门螺杆菌感染与口臭存在极强的相关性\n\n" +
                    "    口臭包括病理性口臭和生理性口臭两种，其中" +
                    "病理性口臭，又包括口源性口臭和非口源性口" +
                    "口源性顾名思义来自口腔，未治疗的龋齿、牙" +
                    "龈炎、牙周炎及口腔粘膜病等都可以引起口" +
                    "臭。\n\n" +
                    "    非口源性来自于某些疾病，例如急慢性胃炎、" +
                    "消化性溃疡出现酸臭味;幽门]梗阻、晚期胃癌" +
                    "常出现臭鸭蛋性口臭;糖尿病酮症酸中毒患者" +
                    "可呼出丙酮味气体，尿毒症患者呼出烂苹果气" +
                    "味。\n\n",
            "    现在很多人觉得，蹲马桶和手机大概是绝配，" +
                    "蹲厕所如果没有手机，心里就会慌慌的，觉得" +
                    "这次马桶白蹲了。如果因为玩手机增加了如厕" +
                    "的时间，那么也会增加痔疮的发病率。\n\n" +
                    "    因为长时间蹲坐在马桶上，会让腹部压力持续" +
                    "增加，使肛门直肠静脉里的血液回流不畅，造" +
                    "成直肠下端黏膜下和肛管皮肤下静脉丛淤血、" +
                    "曲张，形成柔软的静脉团一也就是 通常我们" +
                    "说的痔疮。\n\n" +
                    "    医生建议，排便时间最好控制在五分钟之前，" +
                    "不要养成排便时玩手机和看书的习惯，蹲便时" +
                    "间越久，淤血就会越严重，发生痔疮的可能性" +
                    "就越大。\n\n",
            "    癌症对人类的威胁越来越大，很多的癌症我们" +
                    "并没有研究清楚，但宫颈癌是人类目前研究得" +
                    "最清楚且最有把握能够控制的。\n\n" +
                    "    我们通常采用的是宫颈细胞学涂片(目前比较" +
                    "流行的是TCT)。 通过宫颈涂片，我们把宫颈" +
                    "上脱落的一些细胞，进行细胞学分析，就能知" +
                    "道它是否有癌前病变或癌变。如果TCT怀疑II" +
                    "级II级，医生就会进行阴道镜下活检检查。\n\n" +
                    "    很多人把阴道镜想象成是和胃镜、肠镜一样的" +
                    "有创性检查。其实阴道镜检查是一个非接触性" +
                    "检查，它是无痛的。宫颈是子宫的一部分，子" +
                    "宫本身也属于内脏器官，而人体的内脏器官大" +
                    "部分都是没有痛觉神经的。\n\n"};
    private List<HealthStationInfo> healthStationInfoList = new ArrayList<>();
    private List<Tip> tipList = new ArrayList<>();



    private TextView tv_infopage;
    private ImageButton more_btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WikiPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WikiPageFragment newInstance(String param1, String param2) {
        WikiPageFragment fragment = new WikiPageFragment();
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
        View view=inflater.inflate(R.layout.fragment_wiki_page, container, false);
        ImageButton more_btn=view.findViewById(R.id.ib_moreIcon);
        more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(getActivity(), HealthTipActivity.class);
                startActivity(it);
            }
        });
        ImageButton jiankansudi_btn=view.findViewById(R.id.ib_jiankansudiIcon);
        jiankansudi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(getActivity(), HealthNewsActivity.class);
                startActivity(it);
            }
        });
        ImageButton yaopinchaxun_btn=view.findViewById(R.id.ib_yaopinchaxunIcon);
        yaopinchaxun_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(getActivity(), DrugInquiryActivity.class);
                startActivity(it);
            }
        });
        for (int i = 0; i < healthStationInfoTitles.length; i++) {//循环两遍，只添加一遍的话不足以充满屏幕
            HealthStationInfo healthStationInfo = new HealthStationInfo(healthStationInfoTitles[i]);
            healthStationInfoList.add(healthStationInfo);
        }
        for (int i = 0; i < TipTitles.length; i++) {//循环两遍，只添加一遍的话不足以充满屏幕
            Tip tip = new Tip(TipTitles[i]);
            tipList.add(tip);
        }
        TipAdapter tipAdapter=new TipAdapter(getActivity(),
                R.layout.health_tip_item, tipList);
        HealthStationInfoAdapter adapter=new HealthStationInfoAdapter(getActivity(),
                R.layout.healthstationinfo_item, healthStationInfoList);
        ListView listView = view.findViewById(R.id.lv_healthstation);
        ListView tip_listView = view.findViewById(R.id.lv_healthtip);
        listView.setAdapter(adapter);
        tip_listView.setAdapter(tipAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HealthStationInfo healthStationInfo = healthStationInfoList.get(position);
                Intent i = new Intent(getActivity(), HealthstationActivity.class);
                i.putExtra("healthStationInfoTitlesArray", healthStationInfoTitles[position].toString());
                i.putExtra("healthStationInfoContentArray", healthStationInfoContent[position].toString());
                startActivity(i);
            }
        });
        tip_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tip tip  = tipList.get(position);
                Intent i = new Intent(getActivity(), HealthTipInfoActivity.class);
                i.putExtra("tipTitleArray", TipTitles[position].toString());
                i.putExtra("tipContentArray", TipContent[position].toString());
                startActivity(i);
            }
        });
        return view;
    }
}