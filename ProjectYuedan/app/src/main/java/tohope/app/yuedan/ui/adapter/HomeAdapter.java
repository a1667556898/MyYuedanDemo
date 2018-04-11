package tohope.app.yuedan.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;
import tohope.app.yuedan.util.ToastUtils;
import tohope.app.yuedan.util.glideutil.ImageLoadUtils;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/12.
 */

public class HomeAdapter extends BaseAdapter {

    private Context context;
    ArrayList<String> mdatas = new ArrayList<>();

    public HomeAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.mdatas = datas;
    }

    public void reflash(ArrayList<String> datas) {
        if (null != mdatas) {
            mdatas.clear();
        }
        mdatas = datas;
        notifyDataSetChanged();
    }

    public void loadMore(ArrayList<String> datas) {
        mdatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mdatas == null ? 0 : mdatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mdatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_homelist, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoadUtils.loadImageViewCircle(context, "aaa", holder.imgAvatar, R.mipmap.ic_launcher, R.mipmap.ic_launcher, 0, 0);
        holder.llShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(context, "点击了分享");
            }
        });
        holder.llComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(context, "点击了评论");
            }
        });
        holder.llZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(context, "点击了赞");
            }
        });
        holder.llYueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(context, "点击了约ta");
            }
        });
        return convertView;
    }


    class ViewHolder {
        @Bind(R.id.img_avatar)
        ImageView imgAvatar;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_nickName)
        TextView tvNickName;
        @Bind(R.id.img_sex)
        ImageView imgSex;
        @Bind(R.id.tv_age)
        TextView tvAge;
        @Bind(R.id.tv_address)
        TextView tvAddress;
        @Bind(R.id.img_address)
        ImageView imgAddress;
        @Bind(R.id.ll_addImgs)
        LinearLayout llAddImgs;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_detail)
        TextView tvDetail;
        @Bind(R.id.img_phone)
        ImageView imgPhone;
        @Bind(R.id.img_card)
        ImageView imgCard;
        @Bind(R.id.img_award)
        ImageView imgAward;
        @Bind(R.id.img_share)
        ImageView imgShare;
        @Bind(R.id.ll_share)
        LinearLayout llShare;
        @Bind(R.id.img_comment)
        ImageView imgComment;
        @Bind(R.id.ll_comment)
        LinearLayout llComment;
        @Bind(R.id.img_zan)
        ImageView imgZan;
        @Bind(R.id.ll_zan)
        LinearLayout llZan;
        @Bind(R.id.img_yueta)
        ImageView imgYueta;
        @Bind(R.id.ll_yueta)
        LinearLayout llYueta;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
