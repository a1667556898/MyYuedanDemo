package tohope.app.yuedan.ui.activity;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

import tohope.app.yuedan.customview.MyRefreshView;

/**
 * Created by ChenZhihong on 2017/6/25.
 */

public abstract class BaseDetailAct extends BaseAppAct {
    private MyRefreshView myRefreshView;

    @Override
    public void beforeInitView() {
        setContentView(getContentView());
        initRefreshView();
    }

    public abstract int getContentView();

    public void initRefreshView() {
        if (getContainView() != null) {
            myRefreshView = new MyRefreshView(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            getContainView().addView(myRefreshView, params);
        }
    }

    /**
     * 所要添加的位置
     */
    public abstract RelativeLayout getContainView();

    /**
     * 背景图白色打底
     */
    protected void showRefreshCommon() {
        if (myRefreshView != null) {
            myRefreshView.refreshCommon();
        }
    }

    /**
     * 背景图白色打底，显示加载中
     */
    protected void showRefreshLoading() {
        if (myRefreshView != null) {
            myRefreshView.refreshLoading();
        }
    }

    /**
     * 背景图白色打底，加载成功
     */
    protected void showRefreshSuccess() {
        if (myRefreshView != null) {
            myRefreshView.refreshSuccess();
        }
    }

    /**
     * 背景图白色打底，加载失败
     */
    protected void showRefreshError() {
        if (myRefreshView != null) {
            myRefreshView.refreshError();
        }
    }

    /**
     * 自定义通知
     */
    protected void showToastMessage(int imageId, String msg) {
        if (myRefreshView != null) {
            myRefreshView.showToastMessage(imageId, msg);
        }
    }

    /**
     * 无数据
     */
    protected void showNoData(int imageId, String msg) {
        if (myRefreshView != null) {
            myRefreshView.showNoData(imageId, msg);
        }
    }

    /**
     * 设置刷新的dialog
     */
    protected void showRefreshDialog(boolean hasBg) {
        if (myRefreshView != null) {
            myRefreshView.startRefresh(hasBg);
        }
    }

    /**
     * 停止刷新的dialog
     */
    protected void stopRefreshDialog() {
        if (myRefreshView != null) {
            myRefreshView.stopRefresh();
        }
    }

    /**
     * 操作成功的通知
     */
    protected void showToastMessage(int msgId) {
        showToastMessage(msgId, false);
    }

    /**
     * 操作成功的通知
     */
    protected void showToastMessage(int msgId, boolean isFinish) {
        if (myRefreshView != null) {
            myRefreshView.showToastMessage(msgId, isFinish);
        }
    }

    /**
     * 操作成功的通知
     */
    protected void showToastMessage(String msg) {
        showToastMessage(msg, false);
    }

    /**
     * 操作成功的通知
     */
    protected void showToastMessage(String msg, boolean isFinish) {
        if (myRefreshView != null) {
            myRefreshView.showToastMessage(msg, isFinish);
        }
    }

    /**
     * 自定义通知
     */
    protected void showToastMessage(int imageId, int msgId) {
        showToastMessage(imageId, msgId, false);
    }

    /**
     * 自定义通知
     */
    protected void showToastMessage(int imageId, int msgId, boolean isFinish) {
        if (myRefreshView != null) {
            myRefreshView.showToastMessage(imageId, msgId);
        }
    }

    /**
     * 白底加载失败，重试按钮
     */
    protected void setMyReloadClick(MyRefreshView.LiReloadClick myReloadClick) {
        if (myRefreshView != null) {
            myRefreshView.setLiReloadClick(myReloadClick);
        }
    }
}
