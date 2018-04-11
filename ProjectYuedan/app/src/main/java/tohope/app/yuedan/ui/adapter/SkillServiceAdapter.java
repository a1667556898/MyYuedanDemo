package tohope.app.yuedan.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;
import tohope.app.yuedan.bean.SkillService;

import static tohope.app.yuedan.R.id.tv_Skill_demandorder;

/**
 * Created by Administrator on 2017/4/13.
 */

public class SkillServiceAdapter extends BaseAdapter {

    private List<SkillService> list = new ArrayList<SkillService>();
    private Context mContext;
    private TextView tvSkillDemandorder, tvSkillPhoneorder, tvSkillVideoorder, tvSkillLineprice, tvSkillPhoneprice, tvSkillVideoprice;
    private LinearLayout llSkillLineInput, llSkillPhoneInput, llSkillVideoInput, llSkillLongInput;

    public SkillServiceAdapter(Context context, List<SkillService> list, TextView tvSkillDemandorder, TextView tvSkillPhoneorder,
                               TextView tvSkillVideoorder, LinearLayout llSkillLineInput,
                               LinearLayout llSkillPhoneInput, LinearLayout llSkillVideoInput,
                               LinearLayout llSkillLongInput, TextView tvSkillLineprice,
                               TextView tvSkillPhoneprice, TextView tvSkillVideoprice) {
        this.tvSkillDemandorder = tvSkillDemandorder;
        this.tvSkillPhoneorder = tvSkillPhoneorder;
        this.tvSkillVideoorder = tvSkillVideoorder;
        this.tvSkillLineprice = tvSkillLineprice;
        this.tvSkillPhoneprice = tvSkillPhoneprice;
        this.tvSkillVideoprice = tvSkillVideoprice;
        this.llSkillLineInput = llSkillLineInput;
        this.llSkillPhoneInput = llSkillPhoneInput;
        this.llSkillVideoInput = llSkillVideoInput;
        this.llSkillLongInput = llSkillLongInput;
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_skillservice, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivSkillService.setImageResource(list.get(position).getImage());
        viewHolder.tvSkillService.setText(list.get(position).getName());
        viewHolder.ivSkillService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        if (list.get(position).getNameIsSelect()) {
                            viewHolder.ivSkillService.setImageResource(R.mipmap.called_meg);
                            viewHolder.tvSkillService.setTextColor(mContext.getResources().getColor(R.color.textDarkGray));
                            list.get(position).setNameIsSelect(false);
                        } else {
                            viewHolder.ivSkillService.setImageResource(R.mipmap.called_meo);
                            viewHolder.tvSkillService.setTextColor(mContext.getResources().getColor(R.color.orange));
                            list.get(position).setNameIsSelect(true);
                        }
                        break;
                    case 1:
                        if (list.get(position).getNameIsSelect()) {
                            viewHolder.ivSkillService.setImageResource(R.mipmap.call_herg);
                            viewHolder.tvSkillService.setTextColor(mContext.getResources().getColor(R.color.textDarkGray));
                            list.get(position).setNameIsSelect(false);

                        } else {
                            viewHolder.ivSkillService.setImageResource(R.mipmap.call_hero);
                            viewHolder.tvSkillService.setTextColor(mContext.getResources().getColor(R.color.orange));
                            list.get(position).setNameIsSelect(true);

                        }
                        break;
                    case 2:
                        if (list.get(position).getNameIsSelect()) {
                            viewHolder.ivSkillService.setImageResource(R.mipmap.phoneg);
                            viewHolder.tvSkillService.setTextColor(mContext.getResources().getColor(R.color.textDarkGray));
                            tvSkillPhoneorder.setVisibility(View.GONE);
                            llSkillPhoneInput.setVisibility(View.GONE);
                            tvSkillPhoneprice.setVisibility(View.GONE);
                            list.get(position).setNameIsSelect(false);
                        } else {
                            viewHolder.ivSkillService.setImageResource(R.mipmap.phoneo);
                            viewHolder.tvSkillService.setTextColor(mContext.getResources().getColor(R.color.orange));
                            tvSkillPhoneorder.setVisibility(View.VISIBLE);
                            llSkillPhoneInput.setVisibility(View.VISIBLE);
                            tvSkillPhoneprice.setVisibility(View.VISIBLE);
                            list.get(position).setNameIsSelect(true);
                        }
                        break;
                    case 3:
                        if (list.get(position).getNameIsSelect()) {
                            viewHolder.ivSkillService.setImageResource(R.mipmap.videog);
                            viewHolder.tvSkillService.setTextColor(mContext.getResources().getColor(R.color.textDarkGray));
                            tvSkillVideoorder.setVisibility(View.GONE);
                            llSkillVideoInput.setVisibility(View.GONE);
                            tvSkillVideoprice.setVisibility(View.GONE);
                            list.get(position).setNameIsSelect(false);
                        } else {
                            viewHolder.ivSkillService.setImageResource(R.mipmap.videoo);
                            viewHolder.tvSkillService.setTextColor(mContext.getResources().getColor(R.color.orange));
                            tvSkillVideoorder.setVisibility(View.VISIBLE);
                            llSkillVideoInput.setVisibility(View.VISIBLE);
                            tvSkillVideoprice.setVisibility(View.VISIBLE);
                            list.get(position).setNameIsSelect(true);
                        }
                        break;
                    case 4:
                        if (list.get(position).getNameIsSelect()) {
                            viewHolder.ivSkillService.setImageResource(R.mipmap.long_rangeg);
                            viewHolder.tvSkillService.setTextColor(mContext.getResources().getColor(R.color.textDarkGray));
                            list.get(position).setNameIsSelect(false);
                            llSkillLongInput.setVisibility(View.GONE);
                        } else {
                            viewHolder.ivSkillService.setImageResource(R.mipmap.long_rangeo);
                            viewHolder.tvSkillService.setTextColor(mContext.getResources().getColor(R.color.orange));
                            list.get(position).setNameIsSelect(true);
                            llSkillLongInput.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_Skill_service)
        ImageView ivSkillService;
        @Bind(R.id.tv_Skill_service)
        TextView tvSkillService;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
