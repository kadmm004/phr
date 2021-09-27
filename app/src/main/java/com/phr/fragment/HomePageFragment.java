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

import com.phr.R;
import com.phr.adapter.HealthStationInfoAdapter;
import com.phr.appoint.RegisterActivity;
import com.phr.entity.HealthStationInfo;
import com.phr.wiki.DrugInquiryActivity;
import com.phr.wiki.HealthstationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment {
    private AppointFragment appointPageFragment;
    private WikiPageFragment infoFragment;
    private HomePageFragment mainpageFragment;
    private MinePageFragment minepageFragment;

    private String[] healthStationInfoTitles = {"夏季如何调理肠胃？“三戒”助消化", "慢性病防治基本知识","慢性肾病好治吗","吃什么蔬菜最补血？","多吃水果蔬菜与全麦会降低糖尿病风险"};
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
                    +"\n\n",
            "    慢性肾病是比较常见的一种疾病，由于早期症状并不明显很多患者都是病情急重后才发现。并且严重的慢性肾病还会发展成为尿毒症，因此大多数患者对于慢性肾病的治疗非常重视。那么，慢性肾病好治吗?下面我们就通过文章内容一起了解一下。\n\n",
            "    慢性肾病好治吗?\n\n"
                    +"     慢性肾病由于属于慢性疾病，因此治疗时间比较长，并且对于日常的护理和饮食也有着非常高的要求。日常生活中很多药物对慢性肾病患有有一定的影响，因此在治疗期间需要根据医生指导进行用药。\n\n"
                    +"     慢性肾病治疗需要注意以下几点：\n\n"
                    +"     一、限制蛋白质\n\n"
                    +"     通常建议蛋白质摄取量为每公斤体重 0.6公克，例如体重为50公斤，则每天蛋白质摄取应控制为30克。我们日常吃的食物中，或多或少含有蛋白质，其中肉类、蛋、奶类的蛋白质品质较好，人体 利用率高，可用于修补或维持肌肉强壮，其它如豆类、核果类、面筋制品蔬菜、水果所含的蛋白质品质较差，会制造较多的废物;黄豆及黄豆制品虽属于植物性蛋白 质，但其品质不亚于肉类。\n\n"
                    +"     因此，目前专家学者亦建议限制蛋白质饮食不应将黄豆及其制品列于禁食范围。在蛋白质摄取量严格限制下，必须慎选蛋白质来源，才能充分被人体利用，建议至少每日允许量的2/3由品质好的蛋白质供应。\n\n"
                    +"     二、补充热量\n\n"
                    +"     在限制蛋白质原则下，米饭类的摄取量受到限制，容易造成热量不足，使体内蛋白质消耗、尿素增加，身体日渐消瘦抵抗力差，所以必须多食用高热量、极低蛋白质的食物。每日热量摄取建议：每公斤体重35-45大卡。\n\n"
                    +"     三、药物治疗\n\n"
                    +"     百令片用于肺肾两虚，精气不足所致的久咳虚喘，神疲乏力，不寐健忘，腰膝酸软，月经不调。具有补益肺肾，秘精益气等作用。在肾脏系统，百令片能降低尿蛋白、保护肾脏的双向免疫调节制剂;保护肾脏，改善肾脏疾病患者的伴随及全身症状;可应用于肾小球疾病(慢性肾炎、肾病综合症、糖尿病肾病、慢性肾功能不全、移植肾等)，肾小管疾病。\n\n"
            ,
            "     1、胡萝卜\n\n"
                    +"     胡萝卜含有很高的维生素B、C，同时又含有一种特别的营养素-胡萝卜素，胡萝卜素对补血极有益，用胡萝卜煮汤，是很好的补血汤饮。\n\n"
                    +"    2、菠菜\n\n"
                    +"     又称菠菱菜，是有名的补血食物，含铁质的胡萝卜素相当丰富，所以菠菜可以算是补血蔬菜中的重要食物，其他含有铁质的食物，在果类中以葡萄乾、李子乾、杏子乾、桃子乾为最多。\n\n"
                    +"    3、西红柿\n\n"
                    +"    果色火红，含有丰富的胡萝卜素、维生素C和B族维生素。酸奶的蛋白质成分能促进铁的吸收，因此，把西红柿和酸奶搭配在一起榨出的西红柿酸奶汁是提高体内铁元素吸收的良好来源，可有效补血。\n\n"
                    +"    4、南瓜\n\n"
                    +"    含有蛋白质、胡萝卜素、维生素、人体必需的8种氨基酸、钙、锌、铁、磷等成份。南瓜中还有钴和锌，钴是构成血液中红细胞的重要成分之一;锌则直接影响成熟红细胞的功能;铁质则是制造血红蛋白的基本微量元素，这些都是补血的好原料。\n\n"
            ,"    根据最近发表在《BMJ》杂志上发表的研究中，水果，蔬菜和全谷类食品的高摄入与2型糖尿病的患病风险降低有关。\n\n"
            +"    研究结果表明，即使这些食物作为健康饮食的一部分，在适度增加的情况下也可以帮助预防2型糖尿病。\n\n"
            +"    在第一项研究中，欧洲研究人员研究了血液中维生素C和类胡萝卜素之间的关联。与使用饮食问卷相比，维生素C和类胡萝卜素水平是更可靠的水果和蔬菜摄入量指标。研究人员计算出，每天水果和蔬菜摄入量每增加66克，患2型糖尿病的风险降低25％。\n\n"
            +"    在第二项研究中，美国的研究人员检查了全谷物食品摄入总量和个体与2型糖尿病之间的关联。在调整了生活方式和糖尿病的饮食风险因素后，总谷物消费量最高的类别的2型糖尿病患病率比最低类别的参与者低29％。\n\n"
            +"    对于单个全谷物食品，研究人员发现，一天食用一份或多份全谷物早餐麦片或黑面包伴随着2型糖尿病的发生风险较低（分别为19％和21％）。\n\n"
            +"    对于其他平均摄入量较低的单独全谷物，每周食用两份或以上与每月少于一份相比，燕麦片的风险降低了21％，麸皮的风险降低了15%。\n\n"
            +"    两项研究都是观察性的，因此无法确定原因，并且有可能某些结果可能归因于无法衡量的（混杂）因素。但是，两项研究均考虑了几种众所周知的生活方式风险因素和饮食质量标志物，这些发现支持了其他将健康饮食与更好的健康联系起来的研究。\n\n"
    };
    private List<HealthStationInfo> healthStationInfoList = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
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
        infoFragment=new WikiPageFragment();
        minepageFragment=new MinePageFragment();
        mainpageFragment=new HomePageFragment();
        appointPageFragment =new AppointFragment();

        View view=inflater.inflate(R.layout.fragment_home_page, container, false);
        ImageButton btn_register=view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(getActivity(), RegisterActivity.class);
                startActivity(it);
            }
        });
        ImageButton btn_inqury=view.findViewById(R.id.btn_inqury);
        btn_inqury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setClass(getActivity(), DrugInquiryActivity.class);
                startActivity(it);
            }
        });
        for (int i = 0; i < healthStationInfoTitles.length; i++) {
            HealthStationInfo healthStationInfo = new HealthStationInfo(healthStationInfoTitles[i]);
            healthStationInfoList.add(healthStationInfo);
        }
        HealthStationInfoAdapter adapter=new HealthStationInfoAdapter(getActivity(),
                R.layout.home_item, healthStationInfoList);
        ListView listView = view.findViewById(R.id.lv_healthstation);
        listView.setAdapter(adapter);
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
        // Inflate the layout for this fragment
        return view;
    }
}