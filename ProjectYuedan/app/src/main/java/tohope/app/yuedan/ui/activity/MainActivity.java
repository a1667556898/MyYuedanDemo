package tohope.app.yuedan.ui.activity;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tohope.app.yuedan.R;
import tohope.app.yuedan.app.MyBaseApplication;
import tohope.app.yuedan.customview.NumImageView;
import tohope.app.yuedan.ui.fragment.DemandFragment;
import tohope.app.yuedan.ui.fragment.MineFragment;
import tohope.app.yuedan.ui.fragment.NearbyFragment;
import tohope.app.yuedan.ui.fragment.ReleaseFragment;
import tohope.app.yuedan.ui.fragment.SkillFragment;
import tohope.app.yuedan.ui.popwindow.PopwindowAnim;


public class MainActivity extends BaseFragmentActivity {


    @Bind(R.id.fragment_main)
    FrameLayout fragmentMain;
    @Bind(R.id.img_nearby)
    ImageView imgNearby;
    @Bind(R.id.tv_nearby)
    TextView tvNearby;
    @Bind(R.id.rl_nearby)
    RelativeLayout rlNearby;
    @Bind(R.id.img_demand)
    NumImageView imgDemand;
    @Bind(R.id.tv_demand)
    TextView tvDemand;
    @Bind(R.id.rl_demand)
    RelativeLayout rlDemand;
    @Bind(R.id.img_release)
    ImageView imgRelease;
    @Bind(R.id.tv_release)
    TextView tvRelease;
    @Bind(R.id.rl_release)
    RelativeLayout rlRelease;
    @Bind(R.id.img_skill)
    NumImageView imgSkill;
    @Bind(R.id.tv_skill)
    TextView tvSkill;
    @Bind(R.id.rl_skill)
    RelativeLayout rlSkill;
    @Bind(R.id.img_mine)
    ImageView imgMine;
    @Bind(R.id.tv_mine)
    TextView tvMine;
    @Bind(R.id.rl_mine)
    RelativeLayout rlMine;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;


    public static final int NEARBY = 0;
    public static final int SKILL = 1;
    public static final int DEMAND = 3;
    public static final int MINE = 4;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    //当前选择的下标
    private int currentTabIndex;
    //获取view
    private View views;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(R.layout.activity_main, null);
        ButterKnife.bind(this);
        initData();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, fragments.get(currentTabIndex)).commit();
        //初始化时第一个view执行放大操作
        bigAnimal(imgNearby, tvNearby);
        imgSkill.setNum(1);
    }

    public static void getIntoMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    private void initData() {
        fragments.add(NearbyFragment.newInstance());
        fragments.add(SkillFragment.newInstance());
        fragments.add(ReleaseFragment.newInstance());
        fragments.add(DemandFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        currentTabIndex = 0;
    }

    public void showFragmentByPosition(int targetTabIndex) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 当前选项卡下标
        Fragment currentFragment = fragments.get(currentTabIndex);
        // 目标选项卡下标
        Fragment targetFragment = fragments.get(targetTabIndex);
        //原来的碎片不用判断是否添加了
        if (!targetFragment.isAdded()) { //要显示的碎片没有添加,需要添加
            transaction.hide(currentFragment).add(R.id.fragment_main, targetFragment);
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        transaction.commit();
        switch (currentTabIndex) {
            case NEARBY:
                imgNearby.setImageResource(R.mipmap.home_uncheck);
                tvNearby.setTextColor(getResources().getColor(R.color.textDarkGray));
                smallAnimal(imgNearby, tvNearby);
                break;
            case DEMAND:
                imgDemand.setImageResource(R.mipmap.need_uncheck);
                tvDemand.setTextColor(getResources().getColor(R.color.textDarkGray));
                smallAnimal(imgDemand, tvDemand);
                break;
            case SKILL:
                imgSkill.setImageResource(R.mipmap.skill_uncheck);

                tvSkill.setTextColor(getResources().getColor(R.color.textDarkGray));
                smallAnimal(imgSkill, tvSkill);
                break;
            case MINE:
                imgMine.setImageResource(R.mipmap.my_uncheck);
                tvMine.setTextColor(getResources().getColor(R.color.textDarkGray));
                smallAnimal(imgMine, tvMine);
                break;
        }
        switch (targetTabIndex) {
            case NEARBY:
                imgNearby.setImageResource(R.mipmap.home_check);
                tvNearby.setTextColor(getResources().getColor(R.color.mainbtabRed));
                bigAnimal(imgNearby, tvNearby);
                break;
            case DEMAND:
                imgDemand.setImageResource(R.mipmap.need_check);
                tvDemand.setTextColor(getResources().getColor(R.color.mainbtabRed));
                bigAnimal(imgDemand, tvDemand);
                break;
            case SKILL:
                imgSkill.setImageResource(R.mipmap.skill_check);
                tvSkill.setTextColor(getResources().getColor(R.color.mainbtabRed));
                bigAnimal(imgSkill, tvSkill);
                break;
            case MINE:
                imgMine.setImageResource(R.mipmap.my_check);
                tvMine.setTextColor(getResources().getColor(R.color.mainbtabRed));
                bigAnimal(imgMine, tvMine);
                break;
        }
        //currentTabIndex进行更改,为下次隐藏做准备
        currentTabIndex = targetTabIndex;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rl_nearby, R.id.rl_demand, R.id.rl_release, R.id.rl_skill, R.id.rl_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_nearby:
                showFragmentByPosition(NEARBY);
                break;
            case R.id.rl_demand:
                showFragmentByPosition(DEMAND);
                break;
            case R.id.rl_release:
                new PopwindowAnim(MainActivity.this, views).showPopwindow();
                break;
            case R.id.rl_skill:
                showFragmentByPosition(SKILL);
                break;
            case R.id.rl_mine:
                showFragmentByPosition(MINE);
                break;
        }
    }
    /**
     * 缩放的动画
     *
     * @param imageView
     * @param textView
     */
    public void smallAnimal(ImageView imageView, TextView textView) {
        Animator smallAnimator = AnimatorInflater.loadAnimator(this,
                R.animator.tab_smaller);
        smallAnimator.setTarget(imageView);
        smallAnimator.start();
        // 3-(2) 恢复上次字体
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
    }

    /**
     * 放大的动画
     *
     * @param imageView
     * @param textView
     */
    public void bigAnimal(ImageView imageView, TextView textView) {
        // 2-(1) 放大动画
        Animator bigAnimator = AnimatorInflater.loadAnimator(this,
                R.animator.tab_bigger);
        bigAnimator.setTarget(imageView);
        bigAnimator.start();
        // 2-(2) 字体变小
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);

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
}
