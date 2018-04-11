package tohope.app.yuedan.app;

import android.app.Activity;
import android.app.Application;



import java.util.ArrayList;
import java.util.List;

import tohope.app.yuedan.bean.UserInfo;
import tohope.app.yuedan.util.LogUtils;
import tohope.app.yuedan.util.SharedPreferencesUtils;

/**
 * Created by ChenZhihong on 2017/3/30.
 */

public class MyBaseApplication extends Application {

    public final String TAG = getClass().getSimpleName();
    public static MyBaseApplication instance;
    public static List<Object> activitys = new ArrayList<Object>();

    private String token;
    private UserInfo mUserInfo;
    private boolean isLogin;
    private int loginType;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //SharedPreferences初始化
        initSharedPrefences();
        //网络请求初始化
        initNetRequest();
        //图片加载初始化
        initGlide();

    }

    /**
     * 单例，返回一个实例
     *
     * @return
     */
    public static MyBaseApplication getInstance() {
        if (instance == null) {
        }
        return instance;
    }

    private void initGlide() {

    }

    private void initNetRequest() {

    }

    private void initSharedPrefences() {
        SharedPreferencesUtils.getInstance(this);
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (!activitys.contains(activity))
            activitys.add(activity);
    }

    // 遍历所有Activity并finish
    public void destory() {
        for (Object activity : activitys) {
            ((Activity) activity).finish();
        }
        System.exit(0);
    }

}
