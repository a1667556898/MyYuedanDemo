package tohope.app.yuedan.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;


/**
 * Created by GYH on 2017/4/14.
 */

public class SkillTimeAdapter extends BaseAdapter {
    private String[] week;
    private Context mContext;

    public SkillTimeAdapter(Context context, String [] week) {
        this.week = week;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return week == null ? 0 : week.length;
    }

    @Override
    public Object getItem(int position) {
        return week[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final SkillTimeAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_skillstylecheck, null);
            viewHolder = new SkillTimeAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SkillTimeAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.cbSkillStyle.setText(week[position]);
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
