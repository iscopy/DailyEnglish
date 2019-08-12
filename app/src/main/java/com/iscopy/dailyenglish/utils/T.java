package com.iscopy.dailyenglish.utils;

import android.content.Context;
import android.widget.Toast;

import com.iscopy.dailyenglish.app.DEApplication;

/**
 * 弹窗提示
 * */
public class T {

    // 设置trfa为true时，可以吐司，为false就不能吐了
    private static boolean trfa = true;

    private T() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showShort(CharSequence message) {
        if (trfa) {
            Toast.makeText(DEApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
        }

    }

    public static void showShort(String message) {
        if (trfa) {
            Toast.makeText(DEApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
        }

    }

    public static void showLong(CharSequence message) {
        if (trfa) {
            Toast.makeText(DEApplication.getAppContext(), message, Toast.LENGTH_LONG).show();
        }

    }

    public static void showLong(int message) {
        if (trfa) {
            Toast.makeText(DEApplication.getAppContext(), message, Toast.LENGTH_LONG).show();
        }

    }

    //自定义时间
    public static void showTime(CharSequence message, int duration) {
        if (trfa) {
            Toast.makeText(DEApplication.getAppContext(), message, duration).show();
        }

    }

    //自定义时间
    public static void showTime(int message, int duration) {
        if (trfa) {
            Toast.makeText(DEApplication.getAppContext(), message, duration).show();
        }

    }


}
