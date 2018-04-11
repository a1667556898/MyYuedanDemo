package tohope.app.yuedan.ui.popwindow;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import tohope.app.yuedan.R;
import tohope.app.yuedan.ui.activity.ClassActivity;
import tohope.app.yuedan.ui.activity.PublishSkillActivity;
import tohope.app.yuedan.util.ToastUtils;

/**
 * Created by Administrator on 2017/3/31.
 */

public class PopwindowAnim {
    private Context mContext;
    private View mView, view;
    private LinearLayout animleft, animright;
    private ImageView animrorate;
    private PopupWindow window;
    private ImageView img_NewerLeft;
    private ImageView img_NewerRight;

    public PopwindowAnim() {

    }

    public PopwindowAnim(Context context, View views) {
        this.mContext = context;
        this.mView = views;
    }

    /**
     * 显示popupWindow
     */
    public void showPopwindow() {
        initPop();
        startPropertyAnim(animrorate, 0f, 135f);
        Animation animationup = AnimationUtils.loadAnimation(mContext, R.anim.popshow_anim);
        animleft.startAnimation(animationup);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animationup = AnimationUtils.loadAnimation(mContext, R.anim.popshow_anim);
                animright.startAnimation(animationup);
                animright.setVisibility(View.VISIBLE);
            }
        }, 200); // 延迟0.2秒，再animright运行的run
        //动画监听事件
        animationup.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animleft.setOnClickListener(new PopOnClick());
                animright.setOnClickListener(new PopOnClick());
                img_NewerRight.setOnClickListener(new PopOnClick());
                img_NewerLeft.setOnClickListener(new PopOnClick());

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animrorate.setOnClickListener(new PopOnClick());
    }

    class PopOnClick implements View.OnClickListener

    {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.animleft:
                    ClassActivity.getIntoClassActivity(mContext,"Demand");
                    break;
                case R.id.animright:
                    ClassActivity.getIntoClassActivity(mContext,"Skill");
                    break;
                case R.id.img_left:
                    ToastUtils.getToast(mContext, "点击进入新手必知");
                    break;
                case R.id.img_right:
                    ToastUtils.getToast(mContext, "点击进入新手必知");
                    break;
                case R.id.animrorate:
                    startPropertyAnim(animrorate, 135f, 0f);
                    //向下动画
                    Animation animationdown = AnimationUtils.loadAnimation(mContext, R.anim.pophidden_anim);
                    animleft.startAnimation(animationdown);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Animation animationdown = AnimationUtils.loadAnimation(mContext, R.anim.pophidden_anim);
                            animright.startAnimation(animationdown);
                        }
                    }, 200); // 延迟0.2秒，再animright运行的run
                    /**
                     *动画监听事件
                     */
                    animationdown.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            animright.setVisibility(View.GONE);
                            //关闭pop
                            window.dismiss();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    break;
            }
        }
    }

    /**
     * 初始化视图
     */
    private void initPop() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.popwindow_anim, null);
        animleft = (LinearLayout) view.findViewById(R.id.animleft);
        animright = (LinearLayout) view.findViewById(R.id.animright);
        animrorate = (ImageView) view.findViewById(R.id.animrorate);
        img_NewerLeft = (ImageView) view.findViewById(R.id.img_NewerLeft);
        img_NewerRight = (ImageView) view.findViewById(R.id.img_NewerRight);
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        // 在底部显示向上选弹出
        window.showAtLocation(mView.findViewById(R.id.rl_release),
                Gravity.BOTTOM, 0, 0);
    }

    /**
     * 旋转动画
     *
     * @param viewrotation：旋转对象
     * @param startnuber:开始角度
     * @param endnumber：结束角度
     */
    private void startPropertyAnim(View viewrotation, float startnuber, float endnumber) {
        // 第二个参数"rotation"表明要执行旋转
        // 0f -> 360f，从旋转360度，也可以是负值，负值即为逆时针旋转，正值是顺时针旋转。
        ObjectAnimator anim = ObjectAnimator.ofFloat(viewrotation, "rotation", startnuber, endnumber);

        // 动画的持续时间，执行多久？
        anim.setDuration(500);

        // 回调监听
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
            }
        });

        // 正式开始启动执行动画
        anim.start();
    }


}
