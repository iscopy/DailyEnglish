package com.iscopy.dailyenglish.ui.my;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.adapter.SignDateAdapter;
import com.iscopy.dailyenglish.app.DEApplication;
import com.iscopy.dailyenglish.base.BaseActivity;
import com.iscopy.dailyenglish.databank.sqlite.SignInDao;
import com.iscopy.dailyenglish.model.SignIn;
import com.iscopy.dailyenglish.utils.AppManager;
import com.iscopy.dailyenglish.utils.DateUtil;
import com.iscopy.dailyenglish.widget.IosDialog;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 签到日志
     */
    private List<SignIn> signInList;
    private SignDateAdapter signDateAdapter;
    /**
     * 日期数据，包括星期
     */
    private List<String> stringList;
    private List<String> weeks = new ArrayList<>();
    private int year;
    private int month;
    private int day;
    private int year_today;
    private int month_today;
    private int day_today;
    private boolean today;

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

    @SuppressLint("NewApi")
    @Override
    public void initView(View view) {
        tvTitle.setText("签到日志");
        tvDate.setText(DateUtil.getCurrentYearAndMonth());
        signInList = new ArrayList<>();
        stringList = new ArrayList<>();
        setWeeks();
        signDateAdapter = new SignDateAdapter(getMContext(), signInList, stringList);
        rvDate.setLayoutManager(new GridLayoutManager(getMContext(), 7));
        rvDate.setAdapter(signDateAdapter);
        rvDate.setNestedScrollingEnabled(false);

        year = DateUtil.getCurrentYearAndMonth(3);
        month = DateUtil.getCurrentYearAndMonth(2);
        day = DateUtil.getCurrentYearAndMonth(1);
        year_today = year;
        month_today = month;
        day_today = day;
        //看今天签过到没
        Today();
        //设置本月日期数据
        setDate(year, month);
        //查询本月签到数据
        getSignIn(year, month);
    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @OnClick({R.id.iv_return, R.id.tv_sign_in, R.id.tv_before_month, R.id.tv_next_month})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                AppManager.finishCurrentActivity();
                break;
            case R.id.tv_sign_in:
                if(today){
                    IosDialog.Builder builder = new IosDialog.Builder(getMContext());
                    builder.setMessage("今天已打卡，可以休息了哦，明天还有5个单词等着你哦，如果还想学习就去复习一下吧！");
                    builder.setPositiveButton("我知道了", (dialogInterface, i) -> dialogInterface.dismiss());
                    builder.create().show();
                }else{
                    SignIn signIn = new SignIn(year_today, month_today, day_today);
                    SignInDao.insertOrderOut(signIn, DEApplication.getDb());
                    Today();
                    getSignIn(year, month);
                }
                break;
            case R.id.tv_before_month:
                if(month == 1){
                    month = 12;
                    year -= 1;
                }else{
                    month -=1;
                }
                setDate(year, month);
                getSignIn(year, month);
                break;
            case R.id.tv_next_month:
                if(month == 12){
                    month = 1;
                    year += 1;
                }else{
                    month +=1;
                }
                setDate(year, month);
                getSignIn(year, month);
                break;
            default:
                break;
        }
    }

    /**
     * 获取年月信息
     * @param year
     * @param month
     */
    public void setDate(int year, int month){
        stringList.clear();
        stringList.addAll(weeks);
        //获取当月天数
        int maxDay = DateUtil.getCurrentMonthLastDay(year, month);
        for (int i = 0; i < DateUtil.getFirstDayOfMonth(year, month) - 1; i++) {
            stringList.add("0");
        }
        for (int i = 0; i < maxDay; i++) {
            stringList.add((i+1)+"");
        }
        tvYearMonth.setText(year + "年" + month + "月");
    }
    private void setWeeks(){
        weeks.add("日");
        weeks.add("一");
        weeks.add("二");
        weeks.add("三");
        weeks.add("四");
        weeks.add("五");
        weeks.add("六");
    }

    /**
     * 查询一下看今天签到过没有？
     * @return
     */
    public boolean Today(int year, int month, int day){
        List<SignIn> list = SignInDao.queryOrderOut(DEApplication.getDb(), "select * from signin where year='" + year + "' and month='" + month + "' and day='" + day + "'");
        if(list.size()!=0){
            return true;
        }
        return false;
    }
    @SuppressLint("NewApi")
    public void Today(){
        today = Today(year_today, month_today, day_today);
        if(today){
            tvSignIn.setBackground(getResources().getDrawable(R.drawable.sign_in2));
        }else{
            tvSignIn.setBackground(getResources().getDrawable(R.drawable.sign_in));
        }
    }

    public void getSignIn(int year, int month){
        signInList.clear();
        signInList.addAll(SignInDao.queryOrderOut(DEApplication.getDb(), "select * from signin where year='" + year + "' and month='" + month + "'"));
        signDateAdapter.notifyDataSetChanged();
    }
}
