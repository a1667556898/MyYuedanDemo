package tohope.app.yuedan.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/6.
 */

public abstract class BaseFragmentActivity extends FragmentActivity {
    protected final String Tag = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
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
    }
}
