package tohope.app.yuedan.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tohope.app.yuedan.R;
import tohope.app.yuedan.ui.activity.PublishDemandActivity;
import tohope.app.yuedan.ui.activity.PublishSkillActivity;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ClassGridAdaper extends BaseAdapter {
    private List<String> items = new ArrayList<String>();
    private Context mContext;
    private String tag;

    public ClassGridAdaper(Context context, List<String> item, String tag) {
        this.items = item;
        this.mContext = context;
        this.tag = tag;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ClassGridAdaper.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_button, null);
            viewHolder = new ClassGridAdaper.ViewHolder();
            viewHolder.tv_show = (TextView) convertView.findViewById(R.id.tv_Classifition_name);
            //   viewHolder.iv_hot=(ImageView) convertView.findViewById(R.id.iv_hot);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ClassGridAdaper.ViewHolder) convertView.getTag();
        }
        viewHolder.tv_show.setText(items.get(position));
        viewHolder.tv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("Demand".equals(tag)) {
                    PublishDemandActivity.getIntoPublishDemandActivity(mContext);
                } else {
                    PublishSkillActivity.getIntoPublishSkillActivity(mContext);
                }

            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv_show;
        // ImageView iv_hot;

    }
}
