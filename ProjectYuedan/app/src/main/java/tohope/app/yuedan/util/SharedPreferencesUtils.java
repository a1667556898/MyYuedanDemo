package tohope.app.yuedan.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ChenZhihong on 2017/3/29.
 * SharedPreferences 数据存取
 */

public class SharedPreferencesUtils {

    /**
     * 存储状态及数据存储
     */
    private static SharedPreferences sharedPreferences;

    /**
     * 当前类对象（单利使用）
     */
    private static SharedPreferencesUtils sharedPreferencesUtils;

    public SharedPreferencesUtils() {

    }

    /**
     * 有参构造
     */
    public SharedPreferencesUtils(Context context) {
        sharedPreferences = context.getSharedPreferences("App", Activity.MODE_PRIVATE);
    }

    /**
     * 获取单利
     */
    public static SharedPreferencesUtils getInstance(Context context) {
        if (null == sharedPreferencesUtils) {
            sharedPreferencesUtils = new SharedPreferencesUtils(context);
        }
        return sharedPreferencesUtils;
    }

    /**
     * String
     */
    public boolean putKVP(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 填入键值对(Boolean)
     *
     * @param key   键
     * @param value 值
     */
    public boolean putKVP(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 填入键值对(Float)
     *
     * @param key   键
     * @param value 值
     */
    public boolean putKVP(String key, Float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 填入键值对(Integer)
     *
     * @param key   键
     * @param value 值
     */
    public boolean putKVP(String key, Integer value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 填入键值对(Long)
     *
     * @param key   键
     * @param value 值
     */
    public boolean putKVP(String key, Long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 填入键值对(Set<String>)
     *
     * @param key
     *            键
     * @param value
     *            值
     */
//    public boolean putKVP(String key, Set<String> values) {
//        Editor editor = sharedPreferences.edit();
//        editor.putStringSet(key, values);
//        return editor.commit();
//    }

    /**
     * 根据键获取值（String）
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public String getValueByKeyString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    /**
     * 根据键获取值（Boolean）
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public boolean getValueByKeyBoolean(String key, Boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 根据键获取值（Float）
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public float getValueByKeyFloat(String key, Float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    /**
     * 根据键获取值（Integer）
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public int getValueByKeyInteger(String key, Integer defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    /**
     * 根据键获取值（Long）
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public long getValueByKeyLong(String key, Long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    /**
     * 根据键获取值（ Set<String>）
     *
     * @param key
     *            键
     * @param
     *
     * @return 值
     */
//    public Set<String> getValueByKeySet(String key, Set<String> defValues) {
//        return sharedPreferences.getStringSet(key, defValues);
//    }

    /**
     * 保存实体类 集合等 集合放在实体类里
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public  <T> T getObject(String key, Class<T> clazz) {
        String jsonValue = sharedPreferences.getString(
                key, null);
        if (jsonValue == null || "".equals(jsonValue)) {
            return null;
        }
        return (T) JsonUtil.jsonToObject(jsonValue, clazz);
    }

    public boolean putObject(String key, Object value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, JsonUtil.objetcToJson(value));
        return editor.commit();
    }
}
