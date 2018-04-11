package tohope.app.yuedan.util;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by ChenZhihong on 2017/3/29.
 * 获取验证码工具类 发送过程中暂时不能取消
 */

public class CountDownTimerUtils extends CountDownTimer {
    private TextView tvSmsCode;

    public CountDownTimerUtils(long millisInFuture, long countDownInterval, TextView tvSmsCode) {
        super(millisInFuture, countDownInterval);
        this.tvSmsCode = tvSmsCode;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tvSmsCode.setClickable(false);
        tvSmsCode.setText(millisUntilFinished / 1000 + "s");
    }

    @Override
    public void onFinish() {
        tvSmsCode.setClickable(true);
        tvSmsCode.setText("获取验证码");
    }
}
