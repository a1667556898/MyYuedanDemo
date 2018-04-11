package tohope.app.yuedan.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tohope.app.yuedan.R;
import tohope.app.yuedan.bean.SkillService;
import tohope.app.yuedan.customview.MyTitleBar;
import tohope.app.yuedan.customview.NoScrollGridView;
import tohope.app.yuedan.ui.adapter.SkillServiceAdapter;
import tohope.app.yuedan.ui.adapter.SkillStyleAdapter;
import tohope.app.yuedan.ui.adapter.SkillTimeAdapter;

import static android.R.id.list;

/**
 * Created by GYH on 2017/4/13.
 */

public class PublishSkillActivity extends BaseActivity {
    @Bind(R.id.ngv_PublishSkill_style)
    NoScrollGridView ngvPublishSkillstyle;
    @Bind(R.id.ngv_PublishSkill_service)
    NoScrollGridView ngvPublishSkillservice;
    @Bind(R.id.my_PublishSkill_titie)
    MyTitleBar myPublishSkillTitie;
    @Bind(R.id.tv_PublishSkill_class)
    TextView tvPublishSkillClass;
    @Bind(R.id.tv_PublishSkill_style)
    TextView tvPublishSkillStyle;
    @Bind(R.id.tv_PublishSkill_service)
    TextView tvPublishSkillService;
    @Bind(R.id.tv_Skill_demandorder)
    TextView tvSkillDemandorder;
    @Bind(R.id.tv_Skill_phoneorder)
    TextView tvSkillPhoneorder;
    @Bind(R.id.tv_Skill_videoorder)
    TextView tvSkillVideoorder;
    @Bind(R.id.et_skill_line_input)
    EditText etSkillLineInput;
    @Bind(R.id.ll_skill_line_input)
    LinearLayout llSkillLineInput;
    @Bind(R.id.tv_Skill_lineprice)
    TextView tvSkillLineprice;
    @Bind(R.id.ll_skill_phone_input)
    LinearLayout llSkillPhoneInput;
    @Bind(R.id.tv_Skill_phoneprice)
    TextView tvSkillPhoneprice;
    @Bind(R.id.et_skill_video_input)
    EditText etSkillVideoInput;
    @Bind(R.id.ll_skill_video_input)
    LinearLayout llSkillVideoInput;
    @Bind(R.id.tv_Skill_videoprice)
    TextView tvSkillVideoprice;
    @Bind(R.id.ed_skill_long_input)
    EditText edSkillLongInput;
    @Bind(R.id.ll_skill_long_input)
    LinearLayout llSkillLongInput;
    @Bind(R.id.ngv_PublishSkill_time)
    NoScrollGridView ngvPublishSkillTime;
    @Bind(R.id.et_Skill_business_introduction)
    EditText etSkillBusinessIntroduction;
    @Bind(R.id.gv_skill_uploadphotos)
    GridView gvSkillUploadphotos;
    List<String> list = new ArrayList<>();

    List<SkillService> slist = new ArrayList<SkillService>();
    String[] listtime;
    @Override
    public int getLayoutId() {
        return R.layout.activity_publishskill;
    }

    public static void getIntoPublishSkillActivity(Context context) {
        Intent intent = new Intent(context, PublishSkillActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        list.add("文艺范儿");
        list.add("老司机");
        list.add("逗比");
        list.add("暖心大白");
        list.add("二次元");
        list.add("百事通");
        list.add("可爱呆萌");
        list.add("气质优越");
        list.add("睿智沉稳");
        list.add("其他风格");
        ngvPublishSkillstyle.setAdapter(new SkillStyleAdapter(this, list));

        SkillService skillService1 = new SkillService();
        skillService1.setImage(R.mipmap.called_meg);
        skillService1.setName("Ta来找我");
        skillService1.setNameIsSelect(false);
        SkillService skillService2 = new SkillService();
        skillService2.setImage(R.mipmap.call_herg);
        skillService2.setName("我去找Ta");
        skillService2.setNameIsSelect(false);

        SkillService skillService3 = new SkillService();
        skillService3.setImage(R.mipmap.phoneg);
        skillService3.setName("电话咨询");
        skillService3.setNameIsSelect(false);

        SkillService skillService4 = new SkillService();
        skillService4.setImage(R.mipmap.videog);
        skillService4.setName("视频咨询");
        skillService4.setNameIsSelect(false);
        SkillService skillService5 = new SkillService();
        skillService5.setImage(R.mipmap.long_rangeg);
        skillService5.setName("远程服务");
        skillService5.setNameIsSelect(false);
        slist.add(skillService1);
        slist.add(skillService2);
        slist.add(skillService3);
        slist.add(skillService4);
        slist.add(skillService5);
        ngvPublishSkillservice.setAdapter(new SkillServiceAdapter(this, slist,
                tvSkillDemandorder, tvSkillPhoneorder,
                tvSkillVideoorder,llSkillLineInput,
                llSkillPhoneInput, llSkillVideoInput,
                llSkillLongInput,tvSkillLineprice,
                tvSkillPhoneprice,tvSkillVideoprice));
        listtime=getResources().getStringArray(R.array.skilltime);
        ngvPublishSkillTime.setAdapter(new SkillTimeAdapter(this,listtime));
    }

    @Override
    protected void loadData() {
        super.loadData();
    }

    @Override
    protected void listener() {
        super.listener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
