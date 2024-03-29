package com.iscopy.dailyenglish.utils;

import android.util.Log;

/**
 * 日志
 * */
public class L {

    // 设置trfa为true时，可以打印调试信息，为false就不能打印了
    private static boolean trfa = true;

    private L() {
    }

    /**
     * 打印information日志
     *
     * @param tag 标签
     * @param msg 日志信息
     */
    public static void i(String tag, String msg) {
        if (trfa) {
            Log.i(tag, msg);
        }

    }

    /**
     * 打印information日志
     *
     * @param tag       标签
     * @param msg       日志信息
     * @param throwable 异常
     */
    public static void i(String tag, String msg, Throwable throwable) {
        if (trfa) {
            Log.i(tag, msg, throwable);
        }

    }

    /**
     * 打印verbose日志
     *
     * @param tag 标签
     * @param msg 日志信息
     */
    public static void v(String tag, String msg) {
        if (trfa) {
            Log.v(tag, msg);
        }

    }

    /**
     * 打印verbose日志
     *
     * @param tag       标签
     * @param msg       日志信息
     * @param throwable 异常
     */
    public static void v(String tag, String msg, Throwable throwable) {
        if (trfa) {
            Log.v(tag, msg, throwable);
        }

    }

    /**
     * 打印debug信息
     *
     * @param tag 标签信息
     * @param msg 日志信息
     */
    public static void d(String tag, String msg) {
        if (trfa) {
            Log.d(tag, msg);
        }

    }

    /**
     * 打印debug日志
     *
     * @param tag       标签信息
     * @param msg       日志信息
     * @param throwable 异常
     */
    public static void d(String tag, String msg, Throwable throwable) {
        if (trfa) {
            Log.d(tag, msg, throwable);
        }

    }

    /**
     * 打印warn日志
     *
     * @param tag 标签信息
     * @param msg 日志信息
     */
    public static void w(String tag, String msg) {
        if (trfa) {
            Log.w(tag, msg);
        }

    }

    /**
     * 打印warn日志
     *
     * @param tag       标签信息
     * @param msg       日志信息
     * @param throwable 异常
     */
    public static void w(String tag, String msg, Throwable throwable) {
        if (trfa) {
            Log.w(tag, msg, throwable);
        }

    }

    /**
     * 打印error日志
     *
     * @param tag 标签
     * @param msg 日志信息
     */
    public static void e(String tag, String msg) {
        if (trfa) {
            Log.e(tag, msg);
        }

    }

    /**
     * 打印error日志
     *
     * @param tag       标签
     * @param msg       日志信息
     * @param throwable 异常
     */
    public static void e(String tag, String msg, Throwable throwable) {
        if (trfa) {
            Log.e(tag, msg, throwable);
        }

    }
}
