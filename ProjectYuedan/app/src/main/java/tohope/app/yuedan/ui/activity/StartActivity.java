package tohope.app.yuedan.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.google.gson.Gson;


import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;
import tohope.app.yuedan.api.AppContent;

import tohope.app.yuedan.app.SharePreferenceKey;
import tohope.app.yuedan.bean.MyToken;
import tohope.app.yuedan.util.LogUtils;
import tohope.app.yuedan.util.NetUtils;
import tohope.app.yuedan.util.SharedPreferencesUtils;
import tohope.app.yuedan.util.UIUtils;
import tohope.app.yuedan.util.netutil.MyHttpUtil;
import tohope.app.yuedan.util.netutil.MyNetCallBack;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/11.
 */

public class StartActivity extends BaseActivity {
    @Bind(R.id.tv_start)
    TextView tv_start;
    SharedPreferencesUtils sharedPreferencesUtils;
    boolean isExit;
    String token;

    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        UIUtils.setImmerseLayout(StartActivity.this, tv_start, false);
        sharedPreferencesUtils = SharedPreferencesUtils.getInstance(this);
        //也可以判断用户信息 为null和不为null
        isExit = sharedPreferencesUtils.getValueByKeyBoolean(SharePreferenceKey.ISEXIT, true);
        token = sharedPreferencesUtils.getValueByKeyString(SharePreferenceKey.TOKEN, "app");
        LogUtils.i(Tag, "旧token" + token);
        if (NetUtils.isNetworkAvailable(this)) {
//            网络请求获取token
            Map<String, String> map = new LinkedHashMap<>();
            map.put("sendtype", "APP_GetToken");
//            getInto();//
            MyHttpUtil.myXutilsPost(AppContent.GetToken, map, new MyNetCallBack(this) {
                @Override
                public void MyOnSuccess(String result) {
                    MyToken myToken = new Gson().fromJson(result, MyToken.class);
                    token = myToken.getToken().get(0).getToken();
                    sharedPreferencesUtils.putKVP(SharePreferenceKey.TOKEN, token);
                    getInto();//
                }

                @Override
                public void MyOnFailure(String result) {
                }
            });
        } else {
            getInto();//
        }
    }

    public static void getIntoStartActivity(Context context) {
        Intent intent = new Intent(context, StartActivity.class);
        context.startActivity(intent);
    }


    private void getInto() {
        if (null != token) {
            LogUtils.i(Tag, "新token" + token);
            if (isExit) {//直接跳转第一个界面
                FirstActivity.getIntoFirstActivity(StartActivity.this);
                finish();
            } else {//直接跳转MainActivity界面
                MainActivity.getIntoMainActivity(StartActivity.this);
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
