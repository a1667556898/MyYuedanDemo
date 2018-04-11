package tohope.app.yuedan.util;

import android.util.Log;

/**
 * Created by ChenZhihong on 2017/3/29.
 * 日志类(如果后台返回数据过多 超过4k会显示不全处理方法见下面)
 */

public class LogUtils {

    private enum ManageLogType {
        VERBOSE, DEBUG, INFO, WARN, ERROR, PRINT
    }

    private static boolean isDebuge = true;

    public static void setIsDebuge(boolean is) {
        isDebuge = is;
    }

    private static void print(ManageLogType type, String tag, String msg) {
        if (!isDebuge) {
            return;
        }
        switch (type) {
            case VERBOSE:
                Log.v(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, msg);
                break;
            case INFO:
                Log.i(tag, msg);
                break;
            case WARN:
                Log.w(tag, msg);
                break;
            case ERROR:
                Log.e(tag, msg);
                break;
            case PRINT:
                break;
        }
    }

    public static void v(String tag, String msg) {
        print(ManageLogType.VERBOSE, tag, msg);
    }

    public static void d(String tag, String msg) {
        print(ManageLogType.DEBUG, tag, msg);
    }

    public static void i(String tag, String msg) {
        print(ManageLogType.INFO, tag, msg);
    }

    public static void w(String tag, String msg) {
        print(ManageLogType.WARN, tag, msg);
    }

    public static void e(String tag, String msg) {
        print(ManageLogType.ERROR, tag, msg);
    }

    public static void p(String tag, String msg) {
        print(ManageLogType.PRINT, tag, msg);
    }

    /**
     * 截断输出日志
     */
    public static void logMore(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }
}
