package tohope.app.yuedan.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by ChenZhihong on 2017/6/25.
 */

public abstract class BaseAppAct extends Activity {
    protected final String TAG = getClass().getSimpleName();

    /**
     * 初始化activity传递参数
     */
    public abstract void initIntentParam(Intent intent);

    /**
     * 初始化页面之前的操作，用于不同页面类型的差异化操作，止于模板层，具体页面无需实现
     */
    public abstract void beforeInitView();

    /**
     * 定义页面控件
     */
    public abstract void initView();

    /**
     * 设置页面控件事件和状态
     */
    public abstract void setViewStatus();

    public abstract void onActResume();

    public abstract void onActPause();

    public abstract void onActDestroy();

    public abstract void onActRestart();

    public abstract void onActStop();

    public abstract void onActStart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntentParam(getIntent());
        beforeInitView();
        initView();
        setViewStatus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        onActStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onActResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onActRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        onActStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroy();
    }
}
