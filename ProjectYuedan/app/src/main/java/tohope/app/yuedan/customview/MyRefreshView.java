package tohope.app.yuedan.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tohope.app.yuedan.R;

/**
 * Created by ChenZhihong on 2017/6/21.
 */

public class MyRefreshView extends FrameLayout implements View.OnClickListener {
    public static final int TOAST_DELAY_TIME = 1000;

    public static final int TYPE_COMMON = 0;
    public static final int TYPE_FAIL = 1;
    public static final int TYPE_SUCC = 2;
    public static final int TYPE_LOADING = 3;
    private Context mContext;
    private View mContentView;
    private ImageView iv_view_li_base;
    private TextView tv_view_li_base;
    private LiReloadClick mLiReloadClick;
    private LinearLayout lv_view_li_base;
    private int mCurrentType;


    private RelativeLayout rv_li_refresh_bg;
    private ImageView iv_li_refresh;
    private RelativeLayout rv_li_refresh;
    private TextView tv_li_refresh_back;
    private AnimationDrawable animationDrawable;
    private LinearLayout lv_li_message;
    private ImageView iv_li_message;
    private TextView tv_li_message;
    private Handler handler = new Handler();

    public MyRefreshView(Context context) {
        super(context);
        this.mContext = context;
        initView();
        setViewStatus();
    }


    public MyRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
        setViewStatus();

    }

    public MyRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
        setViewStatus();
    }

    private void initView() {
        mContentView = View.inflate(mContext, R.layout.view_myrefresh, null);
        iv_li_refresh = (ImageView) mContentView.findViewById(R.id.iv_li_refresh);
        rv_li_refresh = (RelativeLayout) mContentView.findViewById(R.id.rv_li_refresh);
        rv_li_refresh_bg = (RelativeLayout) mContentView.findViewById(R.id.rv_li_refresh_bg);
        tv_li_refresh_back = (TextView) mContentView.findViewById(R.id.tv_li_refresh_back);
        lv_li_message = (LinearLayout) mContentView.findViewById(R.id.lv_li_message);
        iv_li_message = (ImageView) mContentView.findViewById(R.id.iv_li_message);
        tv_li_message = (TextView) mContentView.findViewById(R.id.tv_li_message);
        iv_view_li_base = (ImageView) mContentView.findViewById(R.id.iv_view_li_base);
        tv_view_li_base = (TextView) mContentView.findViewById(R.id.tv_view_li_base);
        lv_view_li_base = (LinearLayout) mContentView.findViewById(R.id.lv_view_li_base);
        ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mContentView, llp);
    }

    private void setViewStatus() {
        iv_li_refresh.setImageResource(R.drawable.li_refresh_loading_roate);
        this.setVisibility(GONE);
        lv_view_li_base.setOnClickListener(this);
    }

    /**
     * 开始刷新
     *
     * @param
     * @return
     */
    public void startRefresh(boolean isHasBg) {
        this.setVisibility(VISIBLE);
        rv_li_refresh_bg.setVisibility(VISIBLE);
        lv_view_li_base.setVisibility(GONE);
        if (!isHasBg) {
            rv_li_refresh_bg.setBackground(null);
            iv_li_refresh.setImageResource(R.drawable.li_refresh_loading_roate);
        } else {
            rv_li_refresh_bg.setBackgroundResource(R.drawable.li_refresh_bg);
            iv_li_refresh.setImageResource(R.drawable.animation_list_loading_video);
        }
        hideToast();
        showRefresh();
    }

    /**
     * 结束刷新
     */
    public void stopRefresh() {
        this.setVisibility(GONE);
        hideRefresh();
    }

    /**
     * 展示消息
     */
    public void showToastMessage(int imageId, int msgId) {
        showToastMessage(imageId, msgId, false);
    }

    /**
     * 展示消息
     */
    private void showToastMessage(int imageId, int msgId, boolean isFinish) {
        this.setVisibility(VISIBLE);
        rv_li_refresh_bg.setBackgroundResource(R.drawable.li_refresh_bg);
        hideRefresh();
        showToast(imageId, msgId, isFinish);
    }

    /**
     * 展示消息
     */
    public void showToastMessage(int imageId, String msg) {
        showToastMessage(imageId, msg, false);
    }

    /**
     * 展示消息
     */
    public void showToastMessage(int imageId, String msg, boolean isFinish) {
        this.setVisibility(VISIBLE);
        rv_li_refresh_bg.setBackgroundResource(R.drawable.li_refresh_bg);
        hideRefresh();
        showToast(imageId, msg, isFinish);
    }

    /**
     * 展示消息
     */
    public void showNoData(int imageId, int msgId) {
        this.setVisibility(VISIBLE);
        hideRefresh();
        rv_li_refresh_bg.setBackground(null);
        lv_view_li_base.setVisibility(GONE);
        rv_li_refresh_bg.setVisibility(VISIBLE);
        lv_li_message.setVisibility(VISIBLE);
        iv_li_message.setImageResource(imageId);
        tv_li_message.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.text16));
        tv_li_message.setTextColor(mContext.getResources().getColor(R.color.textDarkGray1));
        tv_li_message.setText(msgId);

    }

    /**
     * 无数据
     */
    public void showNoData(int imgId, String msg) {
        this.setVisibility(VISIBLE);
        hideRefresh();
        rv_li_refresh_bg.setBackground(null);
        rv_li_refresh_bg.setVisibility(VISIBLE);
        lv_li_message.setVisibility(VISIBLE);
        lv_view_li_base.setVisibility(GONE);
        iv_li_message.setImageResource(imgId);
        tv_li_message.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.text16));
        tv_li_message.setTextColor(mContext.getResources().getColor(R.color.textDarkGray1));
        tv_li_message.setText(msg);
    }

    /**
     * 展示操作成功消息
     */
    public void showToastMessage(int msgId, boolean isFinish) {
        showToastMessage(R.drawable.toast_success, msgId, isFinish);
    }

    /**
     * 展示操作成功消息
     */
    public void showToastMessage(String msg, boolean isFinish) {
        showToastMessage(R.drawable.toast_success, msg, isFinish);
    }

    /**
     * 展示操作成功消息
     */
    public void showToastMessage(int msgId) {
        showToastMessage(msgId, false);
    }

    /**
     * 展示操作成功消息
     */
    public void showToastMessage(String msg) {
        showToastMessage(msg, false);
    }

    /**
     * 设置左下角点击事件
     *
     * @param mListener
     */
    public void setBottomBtnClick(OnClickListener mListener) {
        tv_li_refresh_back.setOnClickListener(mListener);
    }

    private void setStatus(int type) {
        this.setVisibility(VISIBLE);
        rv_li_refresh_bg.setVisibility(GONE);
        lv_view_li_base.setVisibility(VISIBLE);
        stopRefresh();
        mCurrentType = type;
        switch (mCurrentType) {
            case TYPE_COMMON:
                iv_view_li_base.setImageResource(R.drawable.li_load_common);
                tv_li_message.setVisibility(GONE);
                break;
            case TYPE_FAIL:
                this.setVisibility(VISIBLE);
                iv_view_li_base.setImageResource(R.drawable.li_load_false);
                iv_view_li_base.setVisibility(VISIBLE);
                tv_view_li_base.setVisibility(VISIBLE);
                tv_view_li_base.setText("点击页面重新加载");
                tv_view_li_base.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.text16));
                break;
            case TYPE_SUCC:
                this.setVisibility(GONE);
                break;
            case TYPE_LOADING:
                this.setVisibility(VISIBLE);
                iv_view_li_base.setImageResource(R.drawable.li_load_common);
                tv_view_li_base.setVisibility(VISIBLE);
                tv_view_li_base.setText("加载中");
                tv_view_li_base.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.text12));
                break;

        }
    }

    /**
     * 刷新错误，重试
     */
    public void refreshError() {
        setStatus(TYPE_FAIL);
    }

    /**
     * 刷新成功，隐藏
     */
    public void refreshSuccess() {
        setStatus(TYPE_SUCC);
    }

    /**
     * 初始化界面
     */
    public void refreshCommon() {
        setStatus(TYPE_COMMON);
    }

    /**
     * 加载中
     */
    public void refreshLoading() {
        setStatus(TYPE_LOADING);
    }

    private void showToast(int imageId, int msgId, final boolean isFinish) {
        lv_li_message.setVisibility(VISIBLE);
        iv_li_refresh.setImageResource(imageId);
        tv_li_message.setText(msgId);
        tv_li_message.setTextColor(getResources().getColor(R.color.white));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideToast();
                MyRefreshView.this.setVisibility(GONE);
                if (isFinish) {
                    ((Activity) mContext).finish();
                }
            }
        }, TOAST_DELAY_TIME);
    }

    private void showToast(int imgId, String msg, final boolean isFinish) {
        lv_li_message.setVisibility(VISIBLE);
        iv_li_message.setImageResource(imgId);
        tv_li_message.setText(msg);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideToast();
                MyRefreshView.this.setVisibility(GONE);
                if (isFinish) {
                    ((Activity) mContext).finish();
                }
            }
        }, TOAST_DELAY_TIME);
    }

    private void hideRefresh() {
        iv_li_refresh.setVisibility(GONE);
        animationDrawable = (AnimationDrawable) iv_li_refresh.getDrawable();
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }

    private void showRefresh() {
        iv_li_refresh.setVisibility(VISIBLE);
        animationDrawable = (AnimationDrawable) iv_li_refresh.getDrawable();
        if (animationDrawable != null) {
            animationDrawable.start();
        }
    }

    private void hideToast() {
        lv_li_message.setVisibility(GONE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lv_view_li_base:
                if (mCurrentType == TYPE_FAIL && mLiReloadClick != null) {
                    refreshLoading();
                    mLiReloadClick.onRefreshClick();
                }
                break;
            case R.id.lv_li_message:
                if (mLiReloadClick != null) {
                    refreshLoading();
                    mLiReloadClick.onRefreshClick();
                }
                break;


        }
    }

    public interface LiReloadClick {
        void onRefreshClick();
    }

    public void setLiReloadClick(LiReloadClick mLiReloadClick) {
        this.mLiReloadClick = mLiReloadClick;
    }
}
