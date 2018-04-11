package tohope.app.yuedan.util.selectionboxutil;

import android.content.Context;
import android.widget.TextView;

import tohope.app.yuedan.R;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/14.
 */

public class SelectBoxUtils {
    //首页订阅选择框
    public static void dingYueBox(Context context, TextView tv1, TextView tv2, TextView t3) {
        tv1.setTextColor(context.getResources().getColor(R.color.titleRed));
        tv2.setTextColor(context.getResources().getColor(R.color.textBlack));
        t3.setTextColor(context.getResources().getColor(R.color.textBlack));
    }
}
