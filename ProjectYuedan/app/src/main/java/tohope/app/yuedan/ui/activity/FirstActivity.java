package tohope.app.yuedan.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tohope.app.yuedan.R;
import tohope.app.yuedan.app.MyBaseApplication;
import tohope.app.yuedan.util.UIUtils;
import tohope.app.yuedan.util.convenientBanner.ConvenBannerUtils;



/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/11.
 */

public class FirstActivity extends BaseActivity {
    @Bind(R.id.img_qq)
    ImageView imgQq;
    @Bind(R.id.img_weichat)
    ImageView imgWeichat;
    @Bind(R.id.rl_qq)
    RelativeLayout rlQq;
    @Bind(R.id.tv_Login)
    TextView tvLogin;
    @Bind(R.id.tv_Regist)
    TextView tvRegist;
    @Bind(R.id.rl_Bg)
    RelativeLayout rlBg;
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;

    ArrayList<String> mTexts=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_first;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        UIUtils.setImmerseLayout(FirstActivity.this, rlBg, false);
        bindBannerBean();
    }

    private void bindBannerBean() {
        mTexts.add("人人分享时间和技能的舞台");
        mTexts.add("人人分享时间和技能的舞台");
        mTexts.add("人人分享时间和技能的舞台");
        mTexts.add("人人分享时间和技能的舞台");
        ConvenBannerUtils.convenBannerNearby1(FirstActivity.this, convenientBanner, mTexts);
    }

    public static void getIntoFirstActivity(Context context) {
        Intent intent = new Intent(context, FirstActivity.class);
        context.startActivity(intent);
    }


    @OnClick({R.id.img_qq, R.id.img_weichat, R.id.tv_Login, R.id.tv_Regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_qq:
                break;
            case R.id.img_weichat:
                break;
            case R.id.tv_Login:
                LoginActivity.getIntoLoginActivity(FirstActivity.this);
                break;
            case R.id.tv_Regist:
                RegistActivity.getIntoRegistActivity(FirstActivity.this);
                break;
        }
    }
    // 再按一次退出程序
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MyBaseApplication.getInstance().destory();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
