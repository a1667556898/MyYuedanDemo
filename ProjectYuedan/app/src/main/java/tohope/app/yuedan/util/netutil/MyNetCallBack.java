package tohope.app.yuedan.util.netutil;

import android.content.Context;
import android.util.Log;


import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import tohope.app.yuedan.util.ToastUtils;


/**
 * Created by ChenZhihong on 2017/3/31.
 */

public abstract class MyNetCallBack extends StringCallback {
    //在这里可以统一管理网络请求 添加加载前动画 根据后台数据进行简单判断等
    private Context context;


    public MyNetCallBack(Context context) {
        this.context = context;

    }

    @Override
    public void onError(Call call, Exception e, int id) {
        Log.i("1234","什么鬼"+e);
        MyOnFailure(e.toString());
    }

    @Override
    public void onResponse(String response, int id) {
        Log.i("1234","什么鬼"+response);
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("Result");
            JSONObject object1 = (JSONObject) array.get(0);
            String State = object1.getString("State");
            String Message = object1.getString("Message");
            if ("ok".equals(State)) {
                MyOnSuccess(response);
            } else {
                ToastUtils.getToast(context, Message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            //如果加载动画则这里结束动画
        }

    }

    public abstract void MyOnSuccess(String result);

    public abstract void MyOnFailure(String result);
}
