package tohope.app.yuedan.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;
import tohope.app.yuedan.customview.MyTitleBar;
import tohope.app.yuedan.customview.YDListView;
import tohope.app.yuedan.customview.YDScrollView;
import tohope.app.yuedan.ui.adapter.DemendAdapter;
import tohope.app.yuedan.util.LogUtils;
import tohope.app.yuedan.util.ToastUtils;
import tohope.app.yuedan.util.UIUtils;

import static tohope.app.yuedan.R.id.convenientBanner;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/6.
 */

public class DemandFragment extends BaseFragment {
    private static final int REFRESH_COMPLETE = 0;
    private static final int REFRESH_COMSHOW = 1;
    @Bind(R.id.myTitle)
    MyTitleBar myTitle;
    @Bind(R.id.myListview)
    YDListView myListview;
    @Bind(R.id.myScrollview)
    YDScrollView myScrollview;
    @Bind(R.id.mySwip)
    SwipeRefreshLayout mySwip;
    @Bind(R.id.tv_publish)
    TextView tvPublish;
    @Bind(R.id.ll_noData)
    LinearLayout llNoData;
    @Bind(R.id.tv_buttom)
    TextView tvButtom;

    private DemendAdapter demendAdapter;
    private ArrayList<String> mDatas;
    private int page;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    demendAdapter.reflash(mDatas);
                    mySwip.setRefreshing(false);
                    break;
                case REFRESH_COMSHOW:
                    demendAdapter.loadMore(mDatas);
                    tvButtom.setVisibility(View.GONE);
                    break;

            }
        }

        ;
    };

    public static DemandFragment newInstance() {

        Bundle args = new Bundle();

        DemandFragment fragment = new DemandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getResId() {
        return R.layout.fragment_demand;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        UIUtils.setImmerseLayout((Activity) getContext(), myTitle.getBg(), true);

    }

    @Override
    protected void loadData(View view) {
        super.loadData(view);
        mDatas = new ArrayList<>();
        demendAdapter = new DemendAdapter(getContext(), mDatas);
        myListview.setAdapter(demendAdapter);
        downLoadDataFromServer(false);
    }

    @Override
    protected void listener() {
        super.listener();
        /**
         * 担保交易监听
         */
        myTitle.getImgLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(getContext(), "跳转到担保交易");
            }
        });
        myTitle.getTextLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(getContext(), "跳转到担保交易");
            }
        });

        /**
         * swipref监听
         */
        mySwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //在这里做联网请求
                page = 0;
                downLoadDataFromServer(false);
            }
        });

        /**
         * scrollview监听
         */
        myScrollview.setScrollChanged(new YDScrollView.MyScrollChangedListener() {
            @Override
            public void OnScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
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
         * 发布需求监听
         */
        tvPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(getContext(), "跳转到发布需求界面");
            }
        });

        /**
         * listview监听
         */
        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtils.getToast(getContext(), "点击了" + i);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
