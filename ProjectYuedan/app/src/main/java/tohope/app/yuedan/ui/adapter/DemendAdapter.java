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

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/12.
 */

public class DemendAdapter extends BaseAdapter {

    private Context context;
    ArrayList<String> mdatas = new ArrayList<>();

    public DemendAdapter(Context context, ArrayList<String> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_demendlist, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.llDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(context, "跳转到查看详情");
            }
        });
        return convertView;
    }


    class ViewHolder {
        @Bind(R.id.img_sex)
        ImageView imgSex;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_state)
        TextView tvState;
        @Bind(R.id.tv_outDateTime)
        TextView tvOutDateTime;
        @Bind(R.id.tv_publishTime)
        TextView tvPublishTime;
        @Bind(R.id.tv_NumDemend)
        TextView tvNumDemend;
        @Bind(R.id.img_noDemend)
        ImageView imgNoDemend;
        @Bind(R.id.tv_wait)
        TextView tvWait;
        @Bind(R.id.ll_addImgs)
        LinearLayout llAddImgs;
        @Bind(R.id.ll_detail)
        LinearLayout llDetail;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
