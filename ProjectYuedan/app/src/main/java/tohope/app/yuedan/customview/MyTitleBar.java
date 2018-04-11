package tohope.app.yuedan.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import tohope.app.yuedan.R;


/**
 * Created by ChenZhihong on 2017/3/30.
 * 自定义标题栏
 */

public class MyTitleBar extends LinearLayout {

    public ImageView imgLeft;
    public ImageView imgRight;
    public TextView textMid;
    public TextView textLeft;
    protected LinearLayout bg;


    public MyTitleBar(Context context) {
        super(context);
        init(context, null);
    }


    public MyTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.title_titlebar, this, true);
        imgLeft = (ImageView) view.findViewById(R.id.img_left);
        imgRight = (ImageView) view.findViewById(R.id.img_right);
        textMid = (TextView) view.findViewById(R.id.text_mid);
        textLeft = (TextView) view.findViewById(R.id.text_left);
        bg = (LinearLayout) view.findViewById(R.id.bg);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
        imgLeft.setImageResource(array.getResourceId(R.styleable.MyTitleBar_imgLeft, 0));
        imgRight.setImageResource(array.getResourceId(R.styleable.MyTitleBar_imgRight, 0));
        textMid.setText(array.getString(R.styleable.MyTitleBar_textMid));
        textMid.setTextColor(array.getColor(R.styleable.MyTitleBar_textMidColor, 0xFFFFFFFF));
        textLeft.setText(array.getString(R.styleable.MyTitleBar_textLeft));
        textLeft.setTextColor(array.getColor(R.styleable.MyTitleBar_textLeftColor, 0xFFFFFFFF));
        bg.setBackground(array.getDrawable(R.styleable.MyTitleBar_bg));
        array.recycle();
    }

    public ImageView getImgLeft() {
        return imgLeft;
    }

    public void setImgLeft(ImageView imgLeft) {
        this.imgLeft = imgLeft;
    }

    public ImageView getImgRight() {
        return imgRight;
    }

    public void setImgRight(ImageView imgRight) {
        this.imgRight = imgRight;
    }

    public TextView getTextMid() {
        return textMid;
    }

    public void setTextMid(TextView textLeft) {
        this.textMid = textMid;
    }

    public TextView getTextLeft() {
        return textLeft;
    }

    public void setTextLeft(TextView textLeft) {
        this.textLeft = textLeft;
    }

    public LinearLayout getBg() {
        return bg;
    }

    protected void setBg(LinearLayout bg) {
        this.bg = bg;
    }
}
