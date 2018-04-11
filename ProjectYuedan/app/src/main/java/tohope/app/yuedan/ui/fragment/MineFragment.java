package tohope.app.yuedan.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tohope.app.yuedan.R;
import tohope.app.yuedan.customview.ImageViewPlus;
import tohope.app.yuedan.customview.MyTitleBar;
import tohope.app.yuedan.ui.activity.FirstActivity;
import tohope.app.yuedan.ui.activity.InformationActity;
import tohope.app.yuedan.util.UIUtils;


/**
 * 逸动无线
 * Created by ChenZhihong And GYH on 2017/4/6.
 */

public class MineFragment extends BaseFragment {
    @Bind(R.id.mtb_mine_myTitle)
    MyTitleBar mtbMineMyTitle;
    @Bind(R.id.ivp_mine_headerimage)
    ImageViewPlus ivpMineHeaderimage;
    @Bind(R.id.tv_mine_name)
    TextView tvMineName;
    @Bind(R.id.tv_mine_sex)
    ImageView tvMineSex;
    @Bind(R.id.tv_mine_age)
    TextView tvMineAge;
    @Bind(R.id.ll_minecenter_quite)
    LinearLayout llMinecenterQuite;
    @Bind(R.id.ll_minecenter_wallet)
    LinearLayout llMinecenterWallet;
    @Bind(R.id.ll_minecenter_album)
    LinearLayout llMinecenterAlbum;
    @Bind(R.id.ll_mine_header)
    LinearLayout llMineHeader;
    private Intent intent = new Intent();
    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getResId() {
        return R.layout.fragment_mine;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        UIUtils.setImmerseLayout((Activity) getContext(), mtbMineMyTitle.getBg(), true);
    }
    @Override
    protected void listener() {
        super.listener();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick({R.id.ivp_mine_headerimage,R.id.ll_minecenter_quite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivp_mine_headerimage:
                InformationActity.getIntoInformationActivity(getContext(),"TagMineFragment",null,null);
                break;
            case R.id.ll_minecenter_quite:
                FirstActivity.getIntoFirstActivity(getContext());
                getActivity().finish();
                break;
        }
    }
}
