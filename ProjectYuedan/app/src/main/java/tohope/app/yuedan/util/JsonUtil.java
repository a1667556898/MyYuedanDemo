package tohope.app.yuedan.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by GXJ on 2016/5/5.
 */
public class JsonUtil {

    /**
     * Jsonת����需要导入gson 包
     */
    public static Object jsonToObject(String json, Class<?> classOfT) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(json, classOfT);
    }

    /**
     * Jsonת����
     */
    public static Object jsonToObject(String json, Type type) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(json, type);
    }

    /**
     * json������ArrayList,����Ϊnew TypeToken<ArrayList<T>>() {},����ӷ���
     */
    public static List<?> jsonToList(String json, TypeToken<?> token) {
        return (List<?>) jsonToObject(json, token.getType());
    }

    /**
     * ����תJson
     */
    public static String objetcToJson(Object object) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(object);
    }
//    /**
//     *
//     * @param object
//     * @return
//     */
//    public static String objectToJson(Object object,boolean bool){
//        return JSON.toJSONString(object, bool);
//    }
}
