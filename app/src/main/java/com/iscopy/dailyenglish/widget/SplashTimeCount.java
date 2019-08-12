package com.iscopy.dailyenglish.widget;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.iscopy.dailyenglish.interfaces.JustRight;

/**
 * 引导页五秒倒计时
 */
public class SplashTimeCount extends CountDownTimer {

    private TextView textView;
    private JustRight justRight;

    public SplashTimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public SplashTimeCount(long millisInFuture, long countDownInterval, TextView textView, JustRight justRight) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
        this.justRight = justRight;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        textView.setText("跳转 "+ millisUntilFinished / 1000);
    }

    @Override
    public void onFinish() {
        justRight.justRight();
    }
}
