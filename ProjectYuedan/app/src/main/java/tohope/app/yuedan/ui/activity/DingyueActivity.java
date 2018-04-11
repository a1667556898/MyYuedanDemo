package tohope.app.yuedan.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import tohope.app.yuedan.R;
import tohope.app.yuedan.customview.MyTitleBar;
import tohope.app.yuedan.customview.NoScrollGridView;
import tohope.app.yuedan.ui.adapter.DingyueAdapter;
import tohope.app.yuedan.util.ToastUtils;
import tohope.app.yuedan.util.UIUtils;


/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/14.
 */

public class DingyueActivity extends BaseActivity {

    @Bind(R.id.myTitle)
    MyTitleBar myTitle;
    @Bind(R.id.myGridView_always)
    NoScrollGridView myGridViewAlways;
    @Bind(R.id.myGridView_local)
    NoScrollGridView myGridViewLocal;
    @Bind(R.id.myGridView_all)
    NoScrollGridView myGridViewAll;
    private ArrayList<String> mDatas;
    private DingyueAdapter mAdapter;

    public static void getIntoDingyueActivity(Context context) {
        Intent intent = new Intent(context, DingyueActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dingyue;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        UIUtils.setImmerseLayout(this, myTitle.getBg(), true);
        mDatas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mDatas.add(i + "哈哈哈");
        }
        mAdapter = new DingyueAdapter(this, mDatas);
        myGridViewAlways.setAdapter(mAdapter);
        myGridViewLocal.setAdapter(mAdapter);
        myGridViewAll.setAdapter(mAdapter);
    }

    @Override
    protected void listener() {
        super.listener();
        myTitle.getImgLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myGridViewAlways.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mDatas.size() - 1) {
                    ToastUtils.getToast(DingyueActivity.this, "随机更换");
                } else {
                    ToastUtils.getToast(DingyueActivity.this, "跳转到" + i + "战点");
                }
            }
        });
        myGridViewLocal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mDatas.size() - 1) {
                    ToastUtils.getToast(DingyueActivity.this, "随机更换");

                } else {
                    ToastUtils.getToast(DingyueActivity.this, "跳转到" + i + "战点");
                }
            }
        });
        myGridViewAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mDatas.size() - 1) {
                    ToastUtils.getToast(DingyueActivity.this, "随机更换");

                } else {
                    ToastUtils.getToast(DingyueActivity.this, "跳转到" + i + "战点");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
