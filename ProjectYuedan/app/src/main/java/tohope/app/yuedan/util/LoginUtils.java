package tohope.app.yuedan.util;

import android.app.Activity;

/**
 * Created by ChenZhihong on 2017/3/29.
 * 登录类
 */

public class LoginUtils {

    public static boolean loginCheck(String phone, String password, Activity context) {
        String msg = login(phone, password);
        if (msg != null) {
            ToastUtils.getToast(context, msg);
            return false;
        } else {
            return true;
        }
    }

    private static String login(String phone, String password) {

        if ("".equals(phone) || phone == null) {
            return "请输入手机号";
        }
        if ("".equals(password) || password == null) {
            return "请输入密码";
        }
        if (!StringUtils.isMobileNum(phone)) {
            return "请输入正确手机号";
        }
        if (password.length() < 6) {
            return "密码不能小于6位";
        }
        if (password.length() > 12) {
            return "密码不能大于12位";
        }
        return null;
    }
}
