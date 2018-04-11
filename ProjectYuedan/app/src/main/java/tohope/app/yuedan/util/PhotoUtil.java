package tohope.app.yuedan.util;
import android.app.Dialog;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;

import tohope.app.yuedan.R;

/**
 * Created by GXJ on 2016/5/16.
 */
public class PhotoUtil {

    // private static PlayMoreDiaolog mPlayMoreDiaolog;
    private static final int IMAGE_FROM_CAMERA = 0x0a1;
    private static final int IMAGE_FROM_PHOTOS = 0xfe2;
    public static final int PHOTORESOULT = 0xaf3;// 结果
    private static String currentTime;
    public static  String IMAGE_UNSPECIFIED = "image/*";
    public static  String CACHE_DIR = "/fhj/" + "images/";
    public static Dialog dialog;
    private static Activity activity;
    public static void showSelectDialog(final Activity context) {
        activity=context;
        View view = View.inflate(context, R.layout.dialog_photeo, null);
        TextView photo = (TextView) view.findViewById(R.id.tv_photo);
        TextView select = (TextView) view.findViewById(R.id.tv_select);
        TextView dissmiss = (TextView) view.findViewById(R.id.tv_dissmiss1);
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);*/
        dialog=new Dialog(context, R.style.dialog);
        //final Dialog dialog = builder.create();
        dialog.setContentView(view);
        dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFromPhotos(context);// 从相相册获取
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = Environment.getExternalStorageState();
                if (status.equals(Environment.MEDIA_MOUNTED)) {
                    getFromCamera(context);// 从相机获取
                } else {
                    // 没有SD卡;
                    Toast.makeText(context, "手机没有SD卡", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        WindowManager wm = context.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();//屏幕的宽
        int height = wm.getDefaultDisplay().getHeight();//屏幕的高
        lp.x = 0;   //新位置X坐标
        lp.y = height/2; //新位置Y坐标
        dialog.onWindowAttributesChanged(lp);//dialog显示在正下方
        //dialog.setAnimationStyle(android.R.style.Animation_InputMethod);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setWindowAnimations(android.R.style.Animation_InputMethod);
        dialog.show();
    }
    // 相机拍照
    public static void getFromCamera(Activity context) {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(getTempHeadFile(context)));
            context.startActivityForResult(intent, IMAGE_FROM_CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 从相册选择
    public static void getFromPhotos(Activity context) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                IMAGE_UNSPECIFIED);
        context.startActivityForResult(intent, IMAGE_FROM_PHOTOS);
    }
    // 剪裁图片
    public static void startPhotoZoom(Uri uri,Activity context) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 130);
        intent.putExtra("outputY", 130);
        intent.putExtra("scale", true);
        Uri imageUri = Uri.fromFile(getTempHeadFile(context));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        try {
            context.startActivityForResult(intent, PHOTORESOULT);
        } catch (Exception e) {
        }
        if (dialog!=null){
            dialog.dismiss();
        }

    }
    // 获取图片的路径
    public static File getTempHeadFile(Activity context) {
        File f = null;
        File head = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
            f = new File(Environment.getExternalStorageDirectory(),
                    CACHE_DIR);
        else
            f = context.getCacheDir();

        if (!f.exists())
            f.mkdirs();
        else {
            if (f.isFile()) {
                f.deleteOnExit();
                f.mkdirs();
            }
        }
        head = new File(f, currentTime + "comment.jpg");
        if (!head.exists()) {
            try {
                head.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        f = null;
        return head;
    }
//    //选择默认图像
//    public  void dialogdissmiss(Integer position){
//        dialog.dismiss();
//        MyInformationActivity activity1 = (MyInformationActivity) activity;
//        activity1.setImage(position);
//    }
}
