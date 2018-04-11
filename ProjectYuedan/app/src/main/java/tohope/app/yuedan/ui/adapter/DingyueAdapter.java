package tohope.app.yuedan.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/13.
 */

public class DingyueAdapter extends BaseAdapter {
    private Context context;
    ArrayList<String> mdatas = new ArrayList<>();

    public DingyueAdapter(Context context, ArrayList<String> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dingyuegrid, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (mdatas.size() - 1 == position) {
            holder.imgLogo.setImageResource(R.mipmap.ico_suiji);
            holder.tvTitle.setVisibility(View.INVISIBLE);
            holder.imgXuanzhuan.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.img_logo)
        ImageView imgLogo;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.img_xuanzhuan)
        ImageView imgXuanzhuan;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
