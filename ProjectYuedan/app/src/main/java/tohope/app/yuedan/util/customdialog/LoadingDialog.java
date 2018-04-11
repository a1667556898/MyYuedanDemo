package tohope.app.yuedan.util.customdialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import tohope.app.yuedan.R;


/**
 * Created by Jack.chen on 2017/6/21 0021.
 * Email:1667556898@qq.com
 */

public class LoadingDialog {
    public Context context;
    Dialog dialog;
    ProgressBar proBar;
    private static volatile LoadingDialog loadingDialog = null;

    public LoadingDialog() {

    }

    public static LoadingDialog getInstance() {
        if (loadingDialog == null) {
            synchronized (LoadingDialog.class) {
                if (loadingDialog == null) {
                    loadingDialog = new LoadingDialog();
                }
            }
        }
        return loadingDialog;
    }

    public void initDialog(Context context) {
        this.context = context;
        dialog = new Dialog(context, R.style.dialog);//1，重新设置dialog的样式
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);//随便写的布局 设置背景等在根节点设置
        proBar = (ProgressBar) view.findViewById(R.id.proBar);
        dialog = new Dialog(context, R.style.loadingDialog);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);//设置基本位置
        lp.x = 0;
        lp.y = 0;
//        lp.alpha=0.5f;
        window.setAttributes(lp);//设置偏移位置 透明度等
        window.setBackgroundDrawable(new BitmapDrawable());//2.需要添加这一句
    }

    public void startDialog() {
        if (null != dialog) {
            dialog.show();
        }
    }

    public void stopDialog() {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
