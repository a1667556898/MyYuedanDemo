package tohope.app.yuedan.customview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * * 逸动无线
 * Created by guoyuehe on 2017/4/10.
 * 自定义ScrollView，监听滑动距离，还有滑到底部
 */
public class YDScrollView  extends ScrollView{
    // Edge-effects don't mix well with the translucent action bar in Android 2.X
    private boolean mDisableEdgeEffects = true;

    private MyScrollChangedListener myScrollChangedListener;


    public interface MyScrollChangedListener {
        void OnScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
        void onScrollBottomListener(boolean isBottom);
    }

    public void setScrollChanged(MyScrollChangedListener listener) {
        this.myScrollChangedListener = listener;
    }

    public YDScrollView(Context context) {
        super(context);
    }

    public YDScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YDScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (myScrollChangedListener != null) {
            myScrollChangedListener.OnScrollChanged(this, l, t, oldl, oldt);
        }
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if(scrollY != 0 && null != myScrollChangedListener){
            myScrollChangedListener.onScrollBottomListener(clampedY);
        }
    }

    @Override
    protected float getTopFadingEdgeStrength() {
        // http://stackoverflow.com/a/6894270/244576
        if (mDisableEdgeEffects && Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return 0.0f;
        }
        return super.getTopFadingEdgeStrength();
    }

    @Override
    protected float getBottomFadingEdgeStrength() {
        // http://stackoverflow.com/a/6894270/244576
        if (mDisableEdgeEffects && Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {

            return 0.0f;
        }
        return super.getBottomFadingEdgeStrength();
    }
}
