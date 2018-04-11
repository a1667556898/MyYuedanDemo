package tohope.app.yuedan.util;

import android.app.Activity;

import static android.R.attr.password;

/**
 * Created by ChenZhihong on 2017/3/29.
 * 注册类 可以用来验证修改密码和找回密码
 */

public class RegistUtils {

    public static boolean registCheck(String code, String password, String phone, Activity context) {
        String msg = regist(code, password, phone);
        if (msg != null) {
            ToastUtils.getToast(context, msg);
            return false;
        } else {
            return true;
        }
    }

    private static String regist(String code, String password, String phone) {
        if (code.equals("") || code == null) {
            return "请输入验证码";
        }
        if (password.equals("") || password == null) {
            return "请输入密码";
        }
        if (phone.equals("") || phone == null) {
            return "请输入手机号";
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

    public static boolean idefCodeCheck(String phone, Activity context) {
        String msg = regist(phone);
        if (msg != null) {
            ToastUtils.getToast(context, msg);
            return false;
        } else {
            return true;
        }
    }

    private static String regist(String phone) {
        if (phone.equals("") || phone == null) {
            return "请输入手机号";
        }
        if (!StringUtils.isMobileNum(phone)) {
            return "请输入正确手机号";
        }
        return null;
    }
}
