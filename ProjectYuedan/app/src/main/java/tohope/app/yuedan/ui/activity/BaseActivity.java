package tohope.app.yuedan.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import tohope.app.yuedan.app.MyBaseApplication;


/**
 * Created by ChenZhihong on 2017/3/30.
 */

public abstract class BaseActivity extends Activity {
    protected final String Tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        MyBaseApplication.getInstance().addActivity(this);
        initView();
        loadData();
        listener();
    }

    /**
     * 检查是否登录
     */
    public boolean isLogin(boolean isTo) {
        if ("account" == null) {
            if (isTo) {
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivityForResult(intent, 100);
            } else
                return false;
            return false;
        } else {
            return true;
        }
    }

    protected void loadData() {
    }

    protected void initView() {
    }

    protected void listener() {
    }

    public abstract int getLayoutId();


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
