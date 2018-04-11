package tohope.app.yuedan.util.glideutil;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import tohope.app.yuedan.R;


/**
 * Created by ChenZhihong on 2017/3/31.
 * 图片加载工具类 此项目用Glide
 * 基础参考：http://blog.csdn.net/shangmingchao/article/details/51125554/；
 * 封装参考参考网址：http://blog.csdn.net/qq_26787115/article/details/52877997；
 *
 * load SD卡资源：load("file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg")
 * load assets 资源：load("file:///android_asset/f003.gif") ；
 * load raw资源：load("Android.resource://com.frank.glide/raw/raw_1")或load("android.resource://com.frank.glide/raw/"+R.raw.raw_1)
 * load drawable资源：load("android.resource://com.frank.glide/drawable/news")
 * 或load("android.resource://com.frank.glide/drawable/"+R.drawable.news)
 */

public class ImageLoadUtils {
    /**
     * Glide特点
     * 使用简单
     * 可配置度高，自适应程度高
     * 支持常见图片格式Jpg png gif webp
     * 支持多种数据源  网络、本地、资源、Assets 等
     * 高效缓存策略 支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
     * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
     * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
     * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity
     */

    //1 默认加载
    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).into(mImageView);
    }
    //2 加载指定大小
    public static void loadImageViewSize(Context context,String path,int width,int height,ImageView mImageview){
        Glide.with(context).load(path).override(width,height).into(mImageview);
    }
    //3 设置加载中以及加载失败的图片
    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int lodingImage, int errorImageView){
        Glide.with(mContext).load(path).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }
    //4 设置加载中以及加载失败图片并且指定大小
    public static void loadImageViewLodingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }
    //5 设置跳过内存缓存
    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).skipMemoryCache(true).into(mImageView);
    }

    //6 设置下载优先级
    public static void loadImageViewPriority(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).priority(Priority.NORMAL).into(mImageView);
    }
    /**
     * 策略解说：
     * <p>
     * all:缓存源资源和转换后的资源
     * <p>
     * none:不作任何磁盘缓存
     * <p>
     * source:缓存源资源
     * <p>
     * result：缓存转换后的资源
     */

    //7 设置缓存策略
    public static void loadImageViewDiskCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
    }
    /**
     * api也提供了几个常用的动画：比如crossFade()
     */

    //8 设置加载动画
    public static void loadImageViewAnim(Context mContext, String path, int anim, ImageView mImageView) {
        Glide.with(mContext).load(path).animate(anim).into(mImageView);
    }
    /**
     * 会先加载缩略图
     */

    //9 设置缩略图支持
    public static void loadImageViewThumbnail(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView);
    }
    /**
     * api提供了比如：centerCrop()、fitCenter()等
     */

    //10 设置动态转换
    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).centerCrop().into(mImageView);
    }

    //11 设置动态GIF加载方式
    public static void loadImageViewDynamicGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asGif().into(mImageView);
    }

    //12 设置静态GIF加载方式
    public static void loadImageViewStaticGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asBitmap().into(mImageView);
    }
    //13 清理磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }

    //14 清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();
    }
    //15 加载有占位符的圆型图片
    public static void loadImageViewCircle(Context mContext,String path,ImageView mImageView,int loadingImage,int errorImage,int boundWith,int boundColor){
        Glide.with(mContext).load(path).centerCrop().placeholder(loadingImage).error(errorImage)
                .transform(new GlideCircleTransform(mContext,boundWith,mContext.getResources().getColor(R.color.colorAccent)))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
    }
    //16 加载有占位符的有弧度的图片
    public static void loadImageViewRound(Context mContext,String path,ImageView mImageView,int loadingImage,int errorImage,int roundSize){
        Glide.with(mContext).load(path).centerCrop().placeholder(loadingImage)
                .transform(new GlideRoundTransform(mContext,roundSize))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);

    }
    //17 加载有占位符的灰度处理的图片
    public static void loadImageViewGray(Context mContext,String path,ImageView mImageView,int loadingImage,int errorImage){

    }
}
