package tohope.app.yuedan.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;
import tohope.app.yuedan.bean.ClassBean;
import tohope.app.yuedan.customview.NoScrollGridView;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ClassListAdapter extends BaseAdapter {

    private List<ClassBean> testBeens = new ArrayList<ClassBean>();
    private Context mContext;
private String tag;
    public ClassListAdapter(Context context, List<ClassBean> testBeen,String tag) {
        this.testBeens = testBeen;
        this.mContext = context;
        this.tag=tag;
    }

    @Override
    public int getCount() {
        return testBeens.size();
    }

    @Override
    public Object getItem(int position) {
        return testBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassListAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_gridview, null);
            viewHolder = new ClassListAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ClassListAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.ivClassifitionTitle.setImageResource(testBeens.get(position).getImages());
        viewHolder.tvClassifitionTitle.setText(testBeens.get(position).getTitle());
        viewHolder.nsgClass.setAdapter(new ClassGridAdaper(mContext, testBeens.get(position).getItems(),tag));
        return convertView;
    }


    class ViewHolder {

        @Bind(R.id.iv_Classifition_Title)
        ImageView ivClassifitionTitle;
        @Bind(R.id.tv_Classifition_Title)
        TextView tvClassifitionTitle;
        @Bind(R.id.nsg_Class)
        NoScrollGridView nsgClass;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);

        }
    }
}
