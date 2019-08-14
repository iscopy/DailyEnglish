package com.iscopy.dailyenglish.ui.my;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 签到日志
 */
public class SignInActivity extends BaseActivity {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    TextView ivRight;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_sign_in)
    TextView tvSignIn;
    @BindView(R.id.tv_before_month)
    TextView tvBeforeMonth;
    @BindView(R.id.tv_year_month)
    TextView tvYearMonth;
    @BindView(R.id.tv_next_month)
    TextView tvNextMonth;
    @BindView(R.id.rv_date)
    RecyclerView rvDate;

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_sign_in;
    }

    @Override
    public void initView(View view) {
        tvTitle.setText("签到日志");
    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @OnClick({R.id.iv_return, R.id.tv_sign_in, R.id.tv_before_month, R.id.tv_next_month})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                break;
            case R.id.tv_sign_in:
                break;
            case R.id.tv_before_month:
                break;
            case R.id.tv_next_month:
                break;
            default:
                break;
        }
    }
}
