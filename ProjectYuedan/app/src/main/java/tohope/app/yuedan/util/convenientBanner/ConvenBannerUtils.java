package tohope.app.yuedan.util.convenientBanner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import tohope.app.yuedan.R;
import tohope.app.yuedan.util.ToastUtils;
import tohope.app.yuedan.util.glideutil.ImageLoadUtils;


/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/6.
 * 轮播图处理类
 */

public class ConvenBannerUtils {
    //本地界面轮播图加载
    public static void convenBannerNearby(final Context context, ConvenientBanner convenientBanner, final ArrayList<String> images) {
        convenientBanner.setPages(new CBViewHolderCreator<MyHolderView>() {

            @Override
            public MyHolderView createHolder() {
                return new MyHolderView();
            }
        }, images)
                .setPageIndicator(new int[]{R.drawable.shap_white_point, R.drawable.shap_red_point});

//        convenientBanner.setManualPageable(false);//设置不能手动影响
        convenientBanner.startTurning(2000);
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {//跳转到点击看大图，把图片集合和position传过去传递过去
                ToastUtils.getToast(context,"点击了"+position);
            }
        });
    }

    static class MyHolderView implements Holder<String> {
        ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            ImageLoadUtils.loadImageViewLoding(context, data, imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        }
    }

    //测试只添加文字
    public static void convenBannerNearby1(final Context context, ConvenientBanner convenientBanner, final ArrayList<String> mTexts) {
        convenientBanner.setPages(new CBViewHolderCreator<MyHolderView1>() {

            @Override
            public MyHolderView1 createHolder() {
                return new MyHolderView1();
            }
        }, mTexts)
                .setPageIndicator(new int[]{R.drawable.shap_white_point, R.drawable.shap_red_point});

//        convenientBanner.setManualPageable(false);//设置不能手动影响
        convenientBanner.startTurning(1000);

    }

    static class MyHolderView1 implements Holder<String> {
        TextView textView;

        @Override
        public View createView(Context context) {
           View view=View.inflate(context, R.layout.item_banner,null);
            textView= (TextView) view.findViewById(R.id.tv_mytest);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            textView.setText(data);
        }
    }
    //测试添加文字和图片
//    public static void convenBannerNearby2(final Context context, ConvenientBanner convenientBanner, final ArrayList<BannerItem> images) {
//        convenientBanner.setPages(new CBViewHolderCreator<MyHolderView2>() {
//
//            @Override
//            public MyHolderView2 createHolder() {
//                return new MyHolderView2();
//            }
//        }, images)
//                .setPageIndicator(new int[]{R.drawable.shap_white_point, R.drawable.shap_red_point});
//
////        convenientBanner.setManualPageable(false);//设置不能手动影响
//        convenientBanner.startTurning(2000);
//        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {//跳转到点击看大图，把图片集合和position传过去传递过去
//                ToastUtils.getToast(context,"点击了"+position);
//            }
//        });
//    }
//
//    static class MyHolderView2 implements Holder<BannerItem> {
//        ImageView imageView;
//        TextView textView;
//
//        @Override
//        public View createView(Context context) {
//            View view=View.inflate(context, R.layout.test_list,null);
//            textView= (TextView) view.findViewById(R.id.tv_mytest);
//            imageView= (ImageView) view.findViewById(R.id.img);
//            return view;
//        }
//
//        @Override
//        public void UpdateUI(Context context, int position, BannerItem data) {
//            ImageLoadUtils.loadImageViewLoding(context, data.getImgurl(), imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
//       textView.setText(data.getTitle());
//        }
//    }
}
