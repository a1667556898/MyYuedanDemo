package tohope.app.yuedan.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ChenZhihong on 2017/3/30.
 * fragment基类
 */

public abstract class BaseFragment extends Fragment {
    protected final String Tag = getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getResId(), container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData(view);
        listener();
    }

    /**
     * 检查是否登录
     */
    public boolean isLogin(boolean isTo) {
        if ("" == null) {
            if (isTo) {
//                Intent intent=new Intent(getActivity(), LoginActivity.class);
//                startActivityForResult(intent,100);
            } else
                return false;
            return false;
        } else {
            return true;
        }
    }

    protected void loadData(View view) {
    }

    protected void initView(View view) {
    }

    protected void listener() {
    }

    public abstract int getResId();

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
