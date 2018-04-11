package tohope.app.yuedan.util.netutil;

import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.Map;

import tohope.app.yuedan.util.LogUtils;

/**
 * Created by ChenZhihong on 2017/3/31.
 */

public class MyHttpUtil {

    public static void myXutilsGet(String url, MyNetCallBack callBack) {
        OkHttpUtils.get().url(url).build().execute(callBack);
    }

    public static void myXutilsPost(String url, Map<String, String> params, MyNetCallBack callBack) {
        OkHttpUtils.post().url(url).params(params).build().execute(callBack);
    }

    /**
     * 上传文件
     * @param url 接口
     * @param file 文件
     * @param callBack
     */
    public static void myXutilsFileUpload(String url, File file,MyNetCallBack callBack) {
        OkHttpUtils.post().url(url).addFile("mFile","image.jpg",file).build().execute(callBack);
    }
}
