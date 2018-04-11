package tohope.app.yuedan.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ChenZhihong on 2017/3/29.
 * Bitmap 管理类
 */

public class BitmapUtils {

    //网络图片转换为bitmap
    public static Bitmap getBitmapFromUrl(String urlpath) {
        Bitmap map = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    //根据数据源转换图片（没有处理过）
    public static Bitmap getBitmapFromByte(byte[] data) {
        //如果图片过大则会产生OOM，重新定义该方法对图片进行压缩
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    //根据数据源转换图片（压缩处理）
    public static Bitmap getBitmapFromByte2(byte[] data, int newWidth, int newHeight) {
        //找到options
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置（true 表示图片加载时不会加载实际内容，只会获取图片的信息，基本包括宽度和高度）
        options.inJustDecodeBounds = true;
        Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        Log.i("BitmapUtils", "没有加载加载0到内存中的Bitmap-->" + bm);
        //从options 中获取基本信息
        int orgHeight = options.outHeight;//原始图片高度
        int orgWidth = options.outWidth;
        int scaleWidth = orgWidth / newWidth;
        int scaleHeight = orgHeight / newHeight;
        //采用一种策略（宽度和高度取最小的比例）
        int scale = scaleWidth > scaleHeight ? scaleHeight : scaleWidth;
        options.inSampleSize = scale;

        //默认图片的ARGB都是8为,所以默认图片的一个像素点占用32位
        //RGB_565表示一共占用16位,但是放弃了透明度,清晰度比不上ARGB_8888,但是内存内存消耗降低了1半
        //RGB_565比起ARGB_4444清晰度要高出很多,虽然都是占用16位
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        //建议使用2的倍数,提高压缩的效率
        //options.inSampleSize = 8;
        //真是需要加载内容到内存中了(所以状态必须修改回来,否则返回图片是null)
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);

    }
}
