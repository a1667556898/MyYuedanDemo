package tohope.app.yuedan.ui.activity;


import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;
import tohope.app.yuedan.app.SharePreferenceKey;
import tohope.app.yuedan.util.SharedPreferencesUtils;
import tohope.app.yuedan.util.UIUtils;


/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/11.
 */

public class YinDaoActivity extends BaseActivity {
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.bt_jinru)
    TextView btJinru;

    private List<View> listviews;//定义数据源集合
    private Drawable[] icons;
    SharedPreferencesUtils sharedPreferencesUtils;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yindao;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        UIUtils.setImmerseLayout(this, vp, false);
        sharedPreferencesUtils = SharedPreferencesUtils.getInstance(this);
        boolean isFirst = sharedPreferencesUtils.getValueByKeyBoolean(SharePreferenceKey.ISFIRST_ENTER, false);
        if (isFirst) {//直接跳转启动页
            StartActivity.getIntoStartActivity(YinDaoActivity.this);
            finish();
        }
        initData();
        vp.setAdapter(new MypagerAdapter());
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < listviews.size(); i++) {
                    if (position == listviews.size() - 1) {
                        btJinru.setVisibility(View.VISIBLE);
                    }
                }
                btJinru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {// 保存状态 进入启动页
                        sharedPreferencesUtils.putKVP(SharePreferenceKey.ISFIRST_ENTER, true);
                        StartActivity.getIntoStartActivity(YinDaoActivity.this);
                        finish();
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initData() {
        listviews = new ArrayList<View>();
        TypedArray array = getResources().obtainTypedArray(R.array.icons);
        icons = new Drawable[array.length()];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = array.getDrawable(i);
        }
        array.recycle();
        for (int i = 0; i < icons.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, // 宽度
                    LinearLayout.LayoutParams.MATCH_PARENT));// 高度
            iv.setImageDrawable(icons[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            listviews.add(iv);
        }
    }

    public class MypagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return listviews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = listviews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = listviews.get(position);
            container.removeView(view);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
