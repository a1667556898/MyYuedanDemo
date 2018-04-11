package tohope.app.yuedan.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;
import tohope.app.yuedan.customview.MyTitleBar;
import tohope.app.yuedan.util.UIUtils;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/6.
 */

public class SkillFragment extends BaseFragment {
    @Bind(R.id.myTitle)
    MyTitleBar myTitle;

    public static SkillFragment newInstance() {

        Bundle args = new Bundle();

        SkillFragment fragment = new SkillFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getResId() {
        return R.layout.fragment_skill;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        UIUtils.setImmerseLayout((Activity) getContext(), myTitle.getBg(), true);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
