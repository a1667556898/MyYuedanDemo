package tohope.app.yuedan.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tohope.app.yuedan.R;

/**
 * chenzhihong  2016/11/9 15:15
 * 自定义switch开关
 */
public class SwitchView extends LinearLayout {
    private ImageView maskImage;
    private boolean open = true;


    private boolean isAninFinish = true;
    private float x;
    private boolean isChangedByTouch = false;
    private OnSwitchChangeListener switchChangeListener;
    private Context mContext;
//    private GradientDrawable drawable;

    public interface OnSwitchChangeListener {
        void onSwitchChanged(boolean open);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwitchView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setBackgroundResource(R.drawable.my_page_switch_backgroubd);
        maskImage = new ImageView(getContext());
        maskImage.setLayoutParams(new LayoutParams(dipToPx(12), dipToPx(12)));
        maskImage.setImageResource(R.drawable.my_page_switch_on);
        addView(maskImage);
        setSwitchStatus(open);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View view = getChildAt(0);
//        drawable = (GradientDrawable) getBackground();
        if (open) {
            view.layout(dipToPx(16), dipToPx(2), dipToPx(28), dipToPx(14));
//            drawable.setColor(getContext().getResources().getColor(R.color.li_common_black));
        } else {
            view.layout(dipToPx(2), dipToPx(2), dipToPx(14), dipToPx(14));
//            drawable.setColor(getContext().getResources().getColor(R.color.white));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dipToPx(30), dipToPx(16));
    }

    public boolean getSwitchStatus() {
        return open;
    }

    public void setSwitchStatus(boolean isOpen) {
        this.open = isOpen;
        if (isOpen) {
            setGravity(Gravity.RIGHT);
            maskImage.setImageResource(R.drawable.my_page_switch_on);
        } else {
            setGravity(Gravity.LEFT);
            maskImage.setImageResource(R.drawable.my_page_switch_off);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x = event.getX();
                break;
            }
            case MotionEvent.ACTION_MOVE: {//滑动转变开关
                if (event.getX() - x > 5 && !open) { // 向右
                    changeStatus();
                } else if (event.getX() - x < -5 && open) { // 向左
                    changeStatus();
                }
                break;
            }
            case MotionEvent.ACTION_UP: {//点击转变开关
                if (Math.abs(event.getX() - x) <= 5) {
                    changeStatus();
                }
                isChangedByTouch = false;
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                isChangedByTouch = false;
                break;
            }
        }
        return true;
    }

    private void changeStatus() {
        if (isAninFinish && !isChangedByTouch) {
            isChangedByTouch = true;
            open = !open;
            isAninFinish = false;
            changeOpenStatusWithAnim(open);
            if (switchChangeListener != null) {
                switchChangeListener.onSwitchChanged(open);
            }
        }
    }

    private void changeOpenStatusWithAnim(boolean open) {
        if (open) {
            // 左到右
            Animation leftToRight = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, getWidth() - maskImage.getWidth() - dipToPx(5), Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
            leftToRight.setDuration(300);
            leftToRight.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    maskImage.setImageResource(R.drawable.my_page_switch_on);
                    maskImage.clearAnimation();
                    setGravity(Gravity.RIGHT);
                    isAninFinish = true;
                }
            });
            maskImage.startAnimation(leftToRight);
        } else {
            // 右到左
            Animation rightToLeft = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, maskImage.getWidth() - getWidth() + dipToPx(5), Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
            rightToLeft.setDuration(300);
            rightToLeft.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    maskImage.setImageResource(R.drawable.my_page_switch_off);
                    maskImage.clearAnimation();
                    setGravity(Gravity.LEFT);
                    isAninFinish = true;
                }
            });
            maskImage.startAnimation(rightToLeft);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public OnSwitchChangeListener getSwitchChangeListener() {
        return switchChangeListener;
    }

    public void setOnSwitchChangeListener(OnSwitchChangeListener switchChangeListener) {
        this.switchChangeListener = switchChangeListener;
    }

    public int dipToPx(int dip) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }
}
