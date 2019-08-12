package com.iscopy.dailyenglish.databank;

import android.content.Context;
import android.content.SharedPreferences;

import com.iscopy.dailyenglish.app.DEApplication;

/**
 * SharedPreferences 轻量级存储
 */
public class SharedPreferencesUtils {

    private static SharedPreferences sharedata  = DEApplication.getAppContext().getSharedPreferences("happy_g_data", 0);
    private static SharedPreferences.Editor editor = sharedata.edit();

    public static boolean saveStringData(Context context, String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getStringData(Context context, String key){
        String data = sharedata.getString(key, null);
        return data;
    }

    public static void saveIntData(Context context, String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getIntData(Context context, String key){
        int value = sharedata.getInt(key, 0);
        return value;
    }

    public static void saveBooleanData(Context context, String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBooleanData(Context context, String key){
        boolean value = sharedata.getBoolean(key, false);
        return value;
    }
    public static boolean getBooleanData(Context context, String key, boolean flag){
        boolean value = sharedata.getBoolean(key, flag);
        return value;
    }

    public static void clearData(Context context) {
        editor.clear().commit();
    }
}
