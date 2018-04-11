package tohope.app.yuedan.util.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import tohope.app.yuedan.R;


/**
 * Created by Jack.chen on 2017/6/21 0021.
 * Email:1667556898@qq.com
 */

public class CustomerDialog {
    private TextView tv_title;
    private TextView tv_ok;
    private TextView tv_cancle;
    private MyOnClick myOnClick;

    public interface MyOnClick {
        void myonclick();
    }

    public void setMyOnClick(MyOnClick myOnClick) {
        this.myOnClick = myOnClick;

    }
    public void showSelectDialog(final Context mcontext, String content, String cancle, String ok) {
        View view = View.inflate(mcontext, R.layout.dialogcustom, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        tv_cancle = (TextView) view.findViewById(R.id.tv_dismiss);
        tv_title.setText(content);
        tv_ok.setText(ok);
        tv_cancle.setText(cancle);
        final Dialog dialog = new Dialog(mcontext, R.style.dialog);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);//设置基本位置
        lp.x = 0;
        lp.y = 0;
//        lp.alpha=0.5f;
        window.setAttributes(lp);//设置偏移位置 透明度等
        window.setBackgroundDrawable(new BitmapDrawable());
        dialog.show();
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (null != myOnClick) {
                    myOnClick.myonclick();
                }
            }
        });
    }
}
