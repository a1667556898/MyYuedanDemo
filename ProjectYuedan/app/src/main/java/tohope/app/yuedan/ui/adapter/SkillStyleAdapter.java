package tohope.app.yuedan.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;

/**
 * Created by GYH on 2017/4/13.
 */

public class SkillStyleAdapter extends BaseAdapter {
    private List<String> list = new ArrayList<String>();
    private Context mContext;

    public SkillStyleAdapter(Context context, List<String> list) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final SkillStyleAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_skillstylecheck, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SkillStyleAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.cbSkillStyle.setText(list.get(position));
        viewHolder.cbSkillStyle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    viewHolder.cbSkillStyle.setTextColor(mContext.getResources().getColor(R.color.orange));

                }else{
                    viewHolder.cbSkillStyle.setTextColor(mContext.getResources().getColor(R.color.textLightGray));
                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.cb_SkillStyle)
        CheckBox cbSkillStyle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
