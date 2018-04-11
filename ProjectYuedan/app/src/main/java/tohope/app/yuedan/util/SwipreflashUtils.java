package tohope.app.yuedan.util;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;

import tohope.app.yuedan.app.MyBaseApplication;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/13.
 */

public class SwipreflashUtils {

    public static void initSwip(final SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.holo_orange_light);
        //设置刷新按钮的指示标的颜色，可以设置多种
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_red_light, android.R.color.darker_gray);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setProgressViewOffset(true,0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100, MyBaseApplication.getInstance().getResources().getDisplayMetrics()));
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

    }
}
