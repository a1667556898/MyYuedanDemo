package tohope.app.yuedan.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *逸动
 * Created by guoyuehe on 2017/4/10.
 */

public class YDListView extends ListView {
    public YDListView(Context context) {
        // TODO Auto-generated method stub
        super(context);
    }

    public YDListView(Context context, AttributeSet attrs) {
        // TODO Auto-generated method stub
        super(context, attrs);
    }

    /**
     *设置不能滑动
     */
    public YDListView(Context context, AttributeSet attrs, int defStyle) {
        // TODO Auto-generated method stub
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
