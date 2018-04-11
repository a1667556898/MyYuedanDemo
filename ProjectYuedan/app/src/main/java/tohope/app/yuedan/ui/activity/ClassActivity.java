package tohope.app.yuedan.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;
import tohope.app.yuedan.bean.ClassBean;
import tohope.app.yuedan.customview.MyTitleBar;
import tohope.app.yuedan.ui.adapter.ClassGridAdaper;
import tohope.app.yuedan.ui.adapter.ClassListAdapter;
import tohope.app.yuedan.util.LogUtils;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ClassActivity extends BaseActivity {
    @Bind(R.id.lv_Clissication_show)
    ListView lvClissicationShow;
    @Bind(R.id.my_Class_titie)
    MyTitleBar myClasstitie;
    List<ClassBean> testBeans = new ArrayList<ClassBean>();
    private String tag;//判断是从技能还是需求过来的标示

    @Override
    public int getLayoutId() {
        return R.layout.activty_classification;
    }


    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        tag = getIntent().getStringExtra("Tag");
        LogUtils.i(Tag, tag);
        getData();
        lvClissicationShow.setAdapter(new ClassListAdapter(this, testBeans, tag));
    }

    public static void getIntoClassActivity(Context context, String tag) {
        Intent intent = new Intent(context, ClassActivity.class);
        intent.putExtra("Tag", tag);
        context.startActivity(intent);
    }

    @Override
    protected void listener() {
        super.listener();
        myClasstitie.getImgLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void getData() {
        ClassBean testBean1 = new ClassBean();
        testBean1.setImages(R.mipmap.leisure_time);
        testBean1.setTitle("休闲娱乐");
        List<String> list1 = new ArrayList<String>();
        list1.add("看电影");
        list1.add("陪聊天");
        list1.add("玩游戏");
        list1.add("品美酒");
        list1.add("逛商场");
        list1.add("看电影");
        testBean1.setItems(list1);
        ClassBean testBean2 = new ClassBean();
        testBean2.setImages(R.mipmap.sports);
        testBean2.setTitle("运动健康");
        List<String> list2 = new ArrayList<String>();
        list2.add("健身");
        list2.add("带跑步");
        list2.add("游泳");
        list2.add("羽毛球");
        list2.add("瑜伽");
        list2.add("网球");
        list2.add("台球");
        list2.add("滑雪");
        list2.add("高尔夫");
        list2.add("户外运动");
        list2.add("武术");
        list2.add("乒乓球");
        list2.add("其他运动");
        testBean2.setItems(list2);
        ClassBean testBean3 = new ClassBean();
        testBean1.setImages(R.mipmap.beauty);

        testBean3.setTitle("时尚丽人");
        List<String> list3 = new ArrayList<String>();
        list3.add("美容");
        list3.add("司仪");
        list3.add("摄影");
        list3.add("美妆");
        list3.add("美发");
        list3.add("美甲");
        list3.add("模特");
        list3.add("摄像");
        list3.add("瘦身");
        list3.add("洗头");
        testBean3.setItems(list3);
        ClassBean testBean4 = new ClassBean();
        testBean4.setImages(R.mipmap.home);
        testBean4.setTitle("居家生活");
        List<String> list4 = new ArrayList<String>();
        list4.add("汽车美容");
        list4.add("家电维修");
        list4.add("跑腿代办");
        list4.add("宠物");
        list4.add("兼职厨师");
        list4.add("家装设计");
        list4.add("物品收纳");
        list4.add("月嫂");
        list4.add("购车指导");
        list4.add("男士SPA");
        list4.add("女士SPA");
        list4.add("母婴咨询");
        testBean4.setItems(list4);
        testBeans.add(testBean1);
        testBeans.add(testBean2);
        testBeans.add(testBean3);
        testBeans.add(testBean4);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
