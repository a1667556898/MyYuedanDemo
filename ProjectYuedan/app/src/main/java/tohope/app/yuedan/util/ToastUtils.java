package tohope.app.yuedan.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ChenZhihong on 2017/3/29.
 * toast 工具类
 */

public class ToastUtils {

    /**
     * 普通toast
     */
    public static Toast getToast(Context context, String cor) {
        Toast toast = Toast.makeText(context, cor, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    /**
     * 设置自定义位置的toast
     */
    public static Toast getGravityToast(Context context, String err) {
        Toast toast = Toast.makeText(context, err, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);//设置位置
        toast.show();
        return toast;
    }

    /**
     * 自定义toast.自定义布局 位置
     */
    public static void getCustomToast(Context context, String str, int id_layout, int id_view) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        View view = View.inflate(context, id_layout, null);
        TextView tv = (TextView) view.findViewById(id_view);
        tv.setText(str);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 快速结束toast
     */
    public static void getShortToast(final Activity activity, final String msg, final long time) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Toast toast = Toast.makeText(activity, msg, Toast.LENGTH_LONG);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }
}
