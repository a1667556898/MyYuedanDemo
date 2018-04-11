package tohope.app.yuedan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tohope.app.yuedan.R;
import tohope.app.yuedan.customview.MyBorderTextview;
import tohope.app.yuedan.customview.MyRefreshView;
import tohope.app.yuedan.customview.SwitchView;
import tohope.app.yuedan.util.ToastUtils;

/**
 * Created by ChenZhihong on 2017/6/29.
 */

public class MyTestActivity extends BaseDetailAct {

    @Bind(R.id.tv_commend)
    TextView tvCommend;
    @Bind(R.id.tv_fail)
    TextView tvFail;
    @Bind(R.id.tv_success)
    TextView tvSuccess;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.tv_toast)
    TextView tvToast;
    @Bind(R.id.tv_toastfinish)
    TextView tvToastfinish;
    @Bind(R.id.Rl_myTest)
    RelativeLayout RlMyTest;
    @Bind(R.id.tv_nodade)
    TextView tv_nodade;
    @Bind(R.id.myswitch)
    SwitchView myswitch;
    @Bind(R.id.myBorderTv)
    MyBorderTextview myBorderTv;

    private ArrayAdapter<String> adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
//                    showRefreshSuccess();
                    stopRefreshDialog();
                    break;
            }
        }
    };

    @Override
    public int getContentView() {
        return R.layout.activity_mytest;
    }

    @Override
    public RelativeLayout getContainView() {
        return (RelativeLayout) findViewById(R.id.Rl_myTest);
    }

    @Override
    public void initIntentParam(Intent intent) {

    }

    @Override
    public void initView() {
//        handler.sendEmptyMessageDelayed(1, 2000);

    }

    @Override
    public void setViewStatus() {
        myswitch = (SwitchView) findViewById(R.id.myswitch);
        myswitch.setOnSwitchChangeListener(new SwitchView.OnSwitchChangeListener() {
            @Override
            public void onSwitchChanged(boolean open) {
                if (open) {
                    showToastMessage("hahah");
                }
            }
        });
        setMyReloadClick(new MyRefreshView.LiReloadClick() {
            @Override
            public void onRefreshClick() {
                ToastUtils.getToast(MyTestActivity.this, "点我了");
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        });
//        initSpinner();
    }

//    private void initSpinner() {
//        spinner_main_edu= (Spinner) findViewById(R.id.spinner_main_edu);
//        button_main_submit= (Button) findViewById(R.id.button_main_submit);
//        text_main_info= (TextView) findViewById(R.id.text_main_info);
//        // 设置数据源
//        String[] strArr = new String[]{"初中", "高中", "中专", "大专", "大本", "研究生"};
//
//        // 构建适配器。Spinner控件常用ArrayAdapter适配器，只显示文本。
//        // ArrayAdapter是数组适配器。第一个参数是上下文对象或者说是环境对象，第二个参数是显示数据的布局id，
//        // 布局id可以自定义布局，也可以使用系统自带的布局。如果使用系统的布局，则使用android.R.layout.的形式来调用。
//        // 第三个参数是需要加载的数据源数组。至于是哪种类型的数组，取决于ArrayAdapter的泛型类型。
//        adapter = new ArrayAdapter<String>(MyTestActivity.this,
//                android.R.layout.simple_list_item_single_choice, strArr);
//        // 给控件设置适配器
//        spinner_main_edu.setAdapter(adapter);
//        spinner_main_edu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                // 方法1：利用AdapterView的getItemAtPosition(position)获取item的内容
//                String data = parent.getItemAtPosition(position).toString();
//
//                // 方法2：利用AdapterView的getSelectedItem()获取item的内容
//                String data2 = parent.getSelectedItem().toString();
//
//                // 方法3：利用adapter的getItem()获取item的内容
//                String data3 = adapter.getItem(position);
//
//                text_main_info.setText(data + ":" + data2 + ":" + data3);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//        button_main_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String data = spinner_main_edu.getSelectedItem().toString();
//                text_main_info.setText(data);
//            }
//        });
//    }


    @Override
    public void onActResume() {

    }

    @Override
    public void onActPause() {

    }

    @Override
    public void onActDestroy() {

    }

    @Override
    public void onActRestart() {

    }

    @Override
    public void onActStop() {

    }

    @Override
    public void onActStart() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.myBorderTv, R.id.tv_nodade, R.id.tv_commend, R.id.tv_fail, R.id.tv_success, R.id.tv_loading, R.id.tv_toast, R.id.tv_toastfinish, R.id.Rl_myTest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myBorderTv:
                ToastUtils.getToast(MyTestActivity.this, "点我了");

                break;
            case R.id.tv_commend:
                showRefreshCommon();//有问题 不现实
                break;
            case R.id.tv_fail:
                showRefreshError();//有问题 点了不加载
                break;
            case R.id.tv_success:
                showRefreshSuccess();

                break;
            case R.id.tv_nodade:
                showNoData(R.mipmap.ic_launcher, "没有数据");
                break;
            case R.id.tv_loading:
//                showRefreshLoading();
                showRefreshDialog(false);
                handler.sendEmptyMessageDelayed(1, 2000);
                break;
            case R.id.tv_toast:
//                showToastMessage("我是toast");
                showToastMessage(R.mipmap.ic_launcher, "我是toast");

                break;
            case R.id.tv_toastfinish:
                showToastMessage("我是toast", true);
//                showToastMessage(R.mipmap.ic_launcher,R.string.agree_comply,true);
                break;
            case R.id.Rl_myTest:
                break;
        }
    }
}
