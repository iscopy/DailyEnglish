package com.iscopy.dailyenglish.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;

import com.iscopy.dailyenglish.databank.DESQLite;
import com.iscopy.dailyenglish.utils.L;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import java.util.Locale;

import cn.jpush.android.api.JPushInterface;

public class DEApplication extends Application {

    private static DEApplication happyGApplication;

    /**
     * 获取可读写的数据库
     */
    public static SQLiteDatabase db;

    /**
     * 语音
     */
    public static TextToSpeech tts;
    /**
     * 极光 id
     */
    public static String registrationId;

    @Override
    public void onCreate() {
        super.onCreate();

        aurora();

        bugly();

        happyGApplication = this;

        db = new DESQLite(this).getReadableDatabase();

        read();
    }

    public void bugly(){
        Beta.initDelay = 1 * 1000;
        Beta.upgradeCheckPeriod = 5 * 1000;
        Bugly.init(getApplicationContext(), "a6b7302bba", false);
    }

    public void aurora() {
        JPushInterface.setDebugMode(false);
        //初始化极光推送
        JPushInterface.init(this);
        registrationId = JPushInterface.getRegistrationID(this);
        L.e("Aurora", "registrationId： " + registrationId);
    }

    public static String getRegistrationId() {
        return registrationId;
    }

    public static void setRegistrationId(String registrationId) {
        DEApplication.registrationId = registrationId;
    }

    public static final DEApplication getAppContext() {
        return happyGApplication;
    }

    public static SQLiteDatabase getDb() {
        return db;
    }

    public static void getTts(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void read(){
        tts = new TextToSpeech(DEApplication.getAppContext(), status -> {
            // 判断是否转化成功
            if (status == TextToSpeech.SUCCESS) {
                //默认设定语言为中文，原生的android貌似不支持中文。
                int result = tts.setLanguage(Locale.CHINESE);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    L.d("--","转化成功");
                } else {
                    //不支持中文就将语言设置为英文
                    tts.setLanguage(Locale.US);
                }
            }
        });
    }

}
