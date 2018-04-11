package tohope.app.yuedan.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;


import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;

import java.util.ArrayList;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tohope.app.yuedan.R;
import tohope.app.yuedan.customview.NoScrollGridView;
import tohope.app.yuedan.customview.YDListView;
import tohope.app.yuedan.customview.YDScrollView;
import tohope.app.yuedan.ui.activity.DingyueActivity;
import tohope.app.yuedan.ui.adapter.HomeAdapter;
import tohope.app.yuedan.ui.adapter.HomeDingyueAdapter;
import tohope.app.yuedan.util.LogUtils;
import tohope.app.yuedan.util.SwipreflashUtils;
import tohope.app.yuedan.util.ToastUtils;
import tohope.app.yuedan.util.UIUtils;
import tohope.app.yuedan.util.convenientBanner.ConvenBannerUtils;
import tohope.app.yuedan.util.selectionboxutil.SelectBoxUtils;


/**
 * 逸动无线
 * Created by ChenZhihong And guoyuehe on 2017/4/6.
 */

public class NearbyFragment extends BaseFragment {

    private static final int REFRESH_COMPLETE = 0;
    private static final int REFRESH_COMSHOW = 1;
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @Bind(R.id.ll_dingyue)
    LinearLayout llDingyue;

    @Bind(R.id.ll_xuanzekuang1)
    LinearLayout llXuanzekuang1;
    @Bind(R.id.myListview)
    YDListView myListview;
    @Bind(R.id.tv_buttom)
    TextView tvButtom;
    @Bind(R.id.ysv_slide)
    YDScrollView ysvSlide;
    @Bind(R.id.spl_refresh)
    SwipeRefreshLayout splRefresh;
    @Bind(R.id.ll_title)
    LinearLayout llTitle;
    @Bind(R.id.ll_address)
    LinearLayout llAddress;
    @Bind(R.id.img_search)
    ImageView imgSearch;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.ll_xuanzekuang)
    LinearLayout llXuanzekuang;
    @Bind(R.id.tv_always)
    TextView tvAlways;
    @Bind(R.id.tv_all)
    TextView tvAll;
    @Bind(R.id.tv_local)
    TextView tvLocal;
    @Bind(R.id.myGridView)
    NoScrollGridView myGridView;

    String img0 = "http://img.1985t.com/uploads/previews/2017/04/0-I69pSYN.jpg";
    String img1 = "http://img.1985t.com/uploads/previews/2017/04/0-v6ew4Qm.jpg";
    String img2 = "http://img.1985t.com/uploads/previews/2017/04/0-8GMlIcH.jpg";
    String img3 = "http://img.1985t.com/uploads/previews/2017/04/0-oobBWD1.jpg";
    private ArrayList<String> imgs = new ArrayList<>();
    private ArrayList<String> mDatas = new ArrayList<>();//假数据
    private ArrayList<String> mdingyueDatas = new ArrayList<>();//订阅假数据
    private HomeAdapter mAdapter;
    private HomeDingyueAdapter homeDingyueAdapter;
    private int Hight1;//标题栏显现需要滑过的高度
    private int Hight2;//选择框显现需要滑过的高度
    private int page = 0;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    mAdapter.reflash(mDatas);
                    splRefresh.setRefreshing(false);
                    break;
                case REFRESH_COMSHOW:
                    mAdapter.loadMore(mDatas);
                    tvButtom.setVisibility(View.GONE);
                    break;

            }
        }

        ;
    };

    public static NearbyFragment newInstance() {
        Bundle args = new Bundle();
        NearbyFragment fragment = new NearbyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getResId() {
        return R.layout.fragment_nearby;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        UIUtils.setImmerseLayout((Activity) getContext(), llTitle, true);
        llTitle.getBackground().setAlpha(0);
        SwipreflashUtils.initSwip(splRefresh);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.i(Tag, hidden + "hha");
        if (hidden == false) {
            ysvSlide.smoothScrollTo(0, 0); //设置开始
        }
    }

    @Override
    protected void loadData(View view) {
        super.loadData(view);
        initBanner();//初始化banner数据
        initDingyue();//初始化订阅数据
        initList();//初始化列表数据
        ysvSlide.smoothScrollTo(0, 0); //设置开始
    }

    private void initList() {
        page = 0;
        mAdapter = new HomeAdapter(getContext(), mDatas);
        myListview.setAdapter(mAdapter);
        downLoadDataFromServer(false);
    }

    private void initDingyue() {

        for (int i = 0; i < 8; i++) {
            mdingyueDatas.add(i + "这是订阅数据");
        }
        homeDingyueAdapter = new HomeDingyueAdapter(getContext(), mdingyueDatas);
        myGridView.setAdapter(homeDingyueAdapter);
    }

    private void initBanner() {
        imgs.add(img0);
        imgs.add(img1);
        imgs.add(img2);
        imgs.add(img3);
        ConvenBannerUtils.convenBannerNearby(getContext(), convenientBanner, imgs);
    }

    @Override
    protected void listener() {
        super.listener();
        /**
         * scrollview的监听
         */
        ysvSlide.setScrollChanged(new YDScrollView.MyScrollChangedListener() {
            @Override
            public void OnScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                //标题栏渐变色
                Hight1 = convenientBanner.getMeasuredHeight() - llTitle.getMeasuredHeight();
                if (t >= 0 && t <= Hight1) {
                    int progress = (int) (new Float(t) / new Float(Hight1) * 255);
                    llTitle.getBackground().setAlpha(progress);
                } else if (t < 0) {
                    llTitle.getBackground().setAlpha(0);
                } else {
                    llTitle.getBackground().setAlpha(255);
                }
                //搜索框显示隐藏
                Hight2 = convenientBanner.getMeasuredHeight() + llDingyue.getMeasuredHeight() - llTitle.getMeasuredHeight();
                if (t < Hight2) {
                    llXuanzekuang.setVisibility(View.GONE);
                } else {
                    llXuanzekuang.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScrollBottomListener(boolean isBottom) {
                if (isBottom && tvButtom.getVisibility() == View.GONE) {
                    tvButtom.setVisibility(View.VISIBLE);
                    page++;
                    downLoadDataFromServer(true);
                }
            }
        });
        /**
         * swipreflashlayout的监听
         */
        splRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //在这里做联网请求
                page = 0;
                downLoadDataFromServer(false);
            }
        });
        /**
         * listview的监听
         */
        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtils.getToast(getContext(), "点击了" + i);
            }
        });
        /**
         * 订阅gridview的监听
         */
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mdingyueDatas.size() - 1) {
                    ToastUtils.getToast(getContext(), "跳转到订阅界面");
                    DingyueActivity.getIntoDingyueActivity(getContext());
                } else {
                    ToastUtils.getToast(getContext(), "跳转到" + i + "网址");
                }
            }
        });
    }

    private void downLoadDataFromServer(final boolean isFromEnd) {
        mDatas = new ArrayList<>();
        LogUtils.i(Tag, mDatas.size() + "数量0");
        for (int i = 0; i < 5; i++) {
            mDatas.add("测试数据" + i);
        }
        //在这请求网络
        if (isFromEnd) {
            LogUtils.i(Tag, mDatas.size() + "数量1");
            mHandler.sendEmptyMessageDelayed(REFRESH_COMSHOW, 500);
        } else {
            LogUtils.i(Tag, mDatas.size() + "数量2");
            mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 500);
        }
    }

    @OnClick({R.id.ll_xuanzekuang1, R.id.ll_address, R.id.tv_always, R.id.tv_local, R.id.tv_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_xuanzekuang1:
                ysvSlide.smoothScrollBy(0, Hight2);
                break;
            case R.id.ll_address:
                ToastUtils.getToast(getContext(), "跳转到地址选择界面");
                break;
            case R.id.tv_always:
                SelectBoxUtils.dingYueBox(getContext(), tvAlways, tvAll, tvLocal);
                ToastUtils.getToast(getContext(), "请求网络刷新经常订阅");
                mdingyueDatas=new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    mdingyueDatas.add(i + "这是订阅数据");
                }
                homeDingyueAdapter.reflash(mdingyueDatas);
                break;
            case R.id.tv_local:
                SelectBoxUtils.dingYueBox(getContext(), tvLocal, tvAll, tvAlways);
                ToastUtils.getToast(getContext(), "请求网络刷新本地订阅");
                mdingyueDatas=new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    mdingyueDatas.add(i + "这是订阅数据");
                }
                homeDingyueAdapter.reflash(mdingyueDatas);
                break;
            case R.id.tv_all:
                SelectBoxUtils.dingYueBox(getContext(), tvAll, tvAlways, tvLocal);
                ToastUtils.getToast(getContext(), "请求网络刷新所有订阅");
                mdingyueDatas=new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    mdingyueDatas.add(i + "这是订阅数据");
                }
                homeDingyueAdapter.reflash(mdingyueDatas);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
