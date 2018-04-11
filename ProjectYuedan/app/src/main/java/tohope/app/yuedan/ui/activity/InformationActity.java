package tohope.app.yuedan.ui.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
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
import tohope.app.yuedan.customview.ImageViewPlus;
import tohope.app.yuedan.customview.MyTitleBar;
import tohope.app.yuedan.util.PhotoUtil;
import tohope.app.yuedan.util.SharedPreferencesUtils;
import tohope.app.yuedan.util.StringUtils;
import tohope.app.yuedan.util.ToastUtils;
import tohope.app.yuedan.util.UIUtils;
import tohope.app.yuedan.util.netutil.MyHttpUtil;
import tohope.app.yuedan.util.netutil.MyNetCallBack;

import static tohope.app.yuedan.R.id.et_input_name;
import static tohope.app.yuedan.R.id.ivp_information_header;
import static tohope.app.yuedan.util.PhotoUtil.PHOTORESOULT;
import static tohope.app.yuedan.util.PhotoUtil.startPhotoZoom;

/**
 * Created by Administrator on 2017/4/11.
 */

public class InformationActity extends BaseActivity {
    @Bind(R.id.mt_Information_myTitle)
    MyTitleBar mtInformationMyTitle;
    @Bind(ivp_information_header)
    ImageViewPlus ivpInformationHeader;
    @Bind(et_input_name)
    EditText etInputName;
    @Bind(R.id.tv_Information_birthday)
    TextView tvInformationBirthday;
    @Bind(R.id.cb_Informtion_man)
    CheckBox cbInformtionMan;
    @Bind(R.id.cb_Informtion_women)
    CheckBox cbInformtionWomen;
    @Bind(R.id.bu_Information_Complete)
    Button buInformationComplete;
    @Bind(R.id.img_check)
    ImageView img_check;
    @Bind(R.id.tv_xieyi)
    TextView tv_xieyi;
    private static final int IMAGE_FROM_CAMERA = 0x0a1;
    private static final int IMAGE_FROM_PHOTOS = 0xfe2;
    //(/sdcard/  目录怎么感觉跟Environment.getExternalStorageDirectory()得到的目录是一个效果？)
    private View views;
    int year = 1990;
    int month = 01;
    int day = 01;
    private String token;
    private String tag;//标志符 从哪进来的
    private String mobile;
    private String password;
    private String face;
    private String name;
    private String sex;
    private String birthday;
    private boolean isCheck = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_information;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        UIUtils.setImmerseLayout(InformationActity.this, mtInformationMyTitle.getBg(), true);
        //初始化
        token = SharedPreferencesUtils.getInstance(this).getValueByKeyString(SharePreferenceKey.TOKEN, "app");
        tag = getIntent().getStringExtra("tag");
        mobile = getIntent().getStringExtra("mobile");
        password = getIntent().getStringExtra("password");

    }

    public static void getIntoInformationActivity(Context context, String tag, String mobile, String password) {
        Intent intent = new Intent(context, InformationActity.class);
        intent.putExtra("tag", tag);
        intent.putExtra("mobile", mobile);
        intent.putExtra("password", password);
        context.startActivity(intent);
    }

    @Override
    protected void listener() {
        super.listener();
    }

    @OnClick({R.id.ivp_information_header, et_input_name, R.id.tv_Information_birthday, R.id.cb_Informtion_man, R.id.cb_Informtion_women, R.id.bu_Information_Complete, R.id.tv_xieyi, R.id.img_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivp_information_header:
                //选取图片
                PhotoUtil.showSelectDialog(InformationActity.this);
                break;
            case et_input_name:
                etInputName.setFocusable(true);
                etInputName.setFocusableInTouchMode(true);
                etInputName.requestFocus();
                name = StringUtils.getEditText(etInputName);
                break;
            case R.id.tv_Information_birthday:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        InformationActity.this.year = year;
                        month = monthOfYear;
                        day = dayOfMonth;
                    }
                }, 1990, 01, 01).show();
                tvInformationBirthday.setText(year + "/" + month + "/" + day);
                birthday = (String) tvInformationBirthday.getText();
                break;
            case R.id.cb_Informtion_man:
                cbInformtionMan.setChecked(true);
                cbInformtionWomen.setChecked(false);
                sex = "1";
                break;
            case R.id.cb_Informtion_women:
                cbInformtionMan.setChecked(false);
                cbInformtionWomen.setChecked(true);
                sex = "0";
                break;
            case R.id.bu_Information_Complete:
                if ("TagRegist".equals(tag)) {//从注册进来的，完成注册
                    if (isCheck) {
                        regist();
                    } else {
                        ToastUtils.getToast(InformationActity.this, "请同意用户协议");
                    }
                } else if ("TagMineFragment".equals(tag)) {//从我的中心进来的

                }
                MainActivity.getIntoMainActivity(InformationActity.this);
                finish();
                break;
            case R.id.tv_xieyi:
                ToastUtils.getToast(InformationActity.this, "跳转到用户协议");
                break;
            case R.id.img_check:
                if (isCheck) {
                    isCheck = false;
                } else {
                    isCheck = true;
                }

                break;
        }
    }

    private void regist() {
        HashMap<String, String> map = new HashMap<>();
        map.put("sendtype", "APP_RegisterAccounts");
        map.put("token", token);
        map.put("regtype", "mobile");
        map.put("mobile", mobile);
        map.put("password", password);
        map.put("face", face);
        map.put("sex", sex);
        map.put("birthday", birthday);
        map.put("name", name);
        MyHttpUtil.myXutilsPost(AppContent.RegisterAccounts, map, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result) {//注册成功 跳转到主界面还是跳转到登录界面
                MainActivity.getIntoMainActivity(InformationActity.this);
            }

            @Override
            public void MyOnFailure(String result) {

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case IMAGE_FROM_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    startPhotoZoom(Uri.fromFile(PhotoUtil.getTempHeadFile(this)), this);
                }
                break;
            case IMAGE_FROM_PHOTOS:
                if (resultCode == Activity.RESULT_OK) {
                    startPhotoZoom(data.getData(), this);
                }
                break;
            case PHOTORESOULT:
                if (resultCode == Activity.RESULT_OK) {
                    Bitmap upload_bitmap = BitmapFactory.decodeFile(PhotoUtil.getTempHeadFile(this)
                            .getPath()); //图片的路径
                    //在这里操作图片的网络请求

                    if (upload_bitmap == null) {
                        return;
                    } else {
                        ivpInformationHeader.setImageBitmap(upload_bitmap);
                    }
                }
                break;
            default:
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
