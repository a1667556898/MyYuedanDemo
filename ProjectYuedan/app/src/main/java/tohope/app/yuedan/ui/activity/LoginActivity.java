package tohope.app.yuedan.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tohope.app.yuedan.R;
import tohope.app.yuedan.customview.MyTitleBar;
import tohope.app.yuedan.util.LogUtils;
import tohope.app.yuedan.util.LoginUtils;
import tohope.app.yuedan.util.StringUtils;
import tohope.app.yuedan.util.ToastUtils;
import tohope.app.yuedan.util.UIUtils;
import tohope.app.yuedan.util.netutil.MyHttpUtil;
import tohope.app.yuedan.util.netutil.MyNetCallBack;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/10.
 */

public class LoginActivity extends BaseActivity {
    @Bind(R.id.myTitle)
    MyTitleBar myTitle;
    @Bind(R.id.Et_PhoneNum)
    EditText EtPhoneNum;
    @Bind(R.id.Et_PassWord)
    EditText EtPassWord;
    @Bind(R.id.tv_Login)
    TextView tvLogin;
    @Bind(R.id.tv_ForgetPassWord)
    TextView tvForgetPassWord;
    @Bind(R.id.tv_GoRegist)
    TextView tvGoRegist;
    @Bind(R.id.img_qq)
    ImageView imgQq;
    @Bind(R.id.img_weichat)
    ImageView imgWeichat;

    private String phoneNum;//电话号码
    private String passWord;//密码
private String url="http://mapi.waiguofang.com/wgf-appmobile-web-0.0.5/house/detail/131142/1";
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        UIUtils.setImmerseLayout(LoginActivity.this, myTitle.getBg(), true);
        LogUtils.i(Tag,"成功"+"hih");
        MyHttpUtil.myXutilsGet(url, new MyNetCallBack(LoginActivity.this) {
            @Override
            public void MyOnSuccess(String result) {
                LogUtils.i(Tag,"成功"+result);
            }

            @Override
            public void MyOnFailure(String result) {
                LogUtils.i(Tag,"失败"+result);
            }
        });
    }

    public static void getIntoLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    @OnClick({R.id.tv_Login, R.id.tv_ForgetPassWord, R.id.tv_GoRegist, R.id.img_qq, R.id.img_weichat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_Login:
                phoneNum = StringUtils.getEditText(EtPhoneNum);
                passWord = StringUtils.getEditText(EtPassWord);
                if (LoginUtils.loginCheck(phoneNum, passWord, LoginActivity.this)) {
                    MainActivity.getIntoMainActivity(LoginActivity.this);
                }
                break;
            case R.id.tv_ForgetPassWord:
                ToastUtils.getToast(LoginActivity.this, "进入忘记密码界面");
                break;
            case R.id.tv_GoRegist:
                RegistActivity.getIntoRegistActivity(LoginActivity.this);
                break;
            case R.id.img_qq:
                break;
            case R.id.img_weichat:
                break;
        }
    }

    @Override
    protected void listener() {
        super.listener();
        myTitle.getImgLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
