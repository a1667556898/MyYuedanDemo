package tohope.app.yuedan.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tohope.app.yuedan.R;

import tohope.app.yuedan.api.AppContent;
import tohope.app.yuedan.app.SharePreferenceKey;
import tohope.app.yuedan.customview.MyTitleBar;
import tohope.app.yuedan.util.CountDownTimerUtils;
import tohope.app.yuedan.util.RegistUtils;
import tohope.app.yuedan.util.SharedPreferencesUtils;
import tohope.app.yuedan.util.StringUtils;
import tohope.app.yuedan.util.ToastUtils;
import tohope.app.yuedan.util.UIUtils;
import tohope.app.yuedan.util.netutil.MyHttpUtil;
import tohope.app.yuedan.util.netutil.MyNetCallBack;


/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/10.
 */

public class RegistActivity extends BaseActivity {
    @Bind(R.id.myTitle)
    MyTitleBar myTitle;
    @Bind(R.id.Et_PhoneNum)
    EditText EtPhoneNum;
    @Bind(R.id.tv_Yuyin)
    TextView tvYuyin;
    @Bind(R.id.Et_IdentCode)
    EditText EtIdentCode;
    @Bind(R.id.tv_getCode)
    TextView tvGetCode;
    @Bind(R.id.Et_PassWord)
    EditText EtPassWord;
    @Bind(R.id.tv_Next)
    TextView tvNext;
    @Bind(R.id.img_qq)
    ImageView imgQq;
    @Bind(R.id.img_weichat)
    ImageView imgWeichat;
    @Bind(R.id.img_eye)
    ImageView imgEye;
    @Bind(R.id.img_agree)
    ImageView imgAgree;
    @Bind(R.id.tv_protocol)
    TextView tvProtocol;

    private String phoneNum;//电话号码
    private String identCode;//验证码
    private String passWord;//密码
    private String token;
    CountDownTimerUtils time;
    private boolean isKanjian;

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        UIUtils.setImmerseLayout(RegistActivity.this, myTitle.getBg(), true);
        time = new CountDownTimerUtils(60000, 1000, tvGetCode);

        token=SharedPreferencesUtils.getInstance(this).getValueByKeyString(SharePreferenceKey.TOKEN,"app");
    }

    public static void getIntoRegistActivity(Context context) {
        Intent intent = new Intent(context, RegistActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.tv_getCode, R.id.tv_Next, R.id.img_qq, R.id.img_weichat, R.id.tv_Yuyin, R.id.img_eye, R.id.img_agree, R.id.tv_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_getCode://获取验证码
                phoneNum = StringUtils.getEditText(EtPhoneNum);
                if (RegistUtils.idefCodeCheck(phoneNum, RegistActivity.this)) {
                    getIdentCode();//获取验证码
                }
                break;
            case R.id.tv_Next://点击下一步相当于注册完成，进入完善资料然后进入主界面
                identCode = StringUtils.getEditText(EtIdentCode);
                passWord = StringUtils.getEditText(EtPassWord);
                if (RegistUtils.registCheck(identCode, passWord, phoneNum, RegistActivity.this)) {
                    checkAccounts();//检查是否注册过
                }
                break;
            case R.id.img_qq://qq登录
                break;
            case R.id.img_weichat://微信登录
                break;
            case R.id.tv_Yuyin://语音获取验证码
                break;
            case R.id.img_eye://眼睛
                if (!isKanjian) {//可见密码
                    imgEye.setImageResource(R.mipmap.img_eye);
                    EtPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isKanjian = true;
                } else {// 隐藏密码
                    imgEye.setImageResource(R.mipmap.img_eye);
                    EtPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isKanjian = false;
                }
                break;
            case R.id.img_agree://同意协议按钮
                break;
            case R.id.tv_protocol://进入协议web页面
                ToastUtils.getToast(RegistActivity.this, "进入协议页面");
                break;
        }
    }

    private void checkAccounts() {//注册前判断是否同意用户协议点击下一步相当于注册完成，进入完善资料然后进入主界面
      //检查账号是否存在
        HashMap<String,String> map=new HashMap<>();
        map.put("sendtype","APP_CheckAccounts");
        map.put("token",token );
        map.put("loginname",phoneNum);
        map.put("regtype","mobile");
        MyHttpUtil.myXutilsPost(AppContent.CheckAccounts, map, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result) {
                InformationActity.getIntoInformationActivity(RegistActivity.this,"TagRegist",phoneNum,passWord);
            }

            @Override
            public void MyOnFailure(String result) {

            }
        });

    }

    private void getIdentCode() {//倒计时60s
        HashMap<String,String> map=new HashMap<>();
        map.put("sendtype","APP_GetVerificationCode");
        map.put("token",token );
        map.put("mobile",phoneNum);
        MyHttpUtil.myXutilsPost(AppContent.GetVerificationCode, map, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result) {
                ToastUtils.getToast(RegistActivity.this, "正在获取验证码");
                time.start();
            }

            @Override
            public void MyOnFailure(String result) {

            }
        });

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
