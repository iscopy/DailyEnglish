package com.iscopy.dailyenglish.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.adapter.SplashAdapter;
import com.iscopy.dailyenglish.app.DEApplication;
import com.iscopy.dailyenglish.base.BaseActivity;
import com.iscopy.dailyenglish.constant.Config;
import com.iscopy.dailyenglish.databank.SharedPreferencesUtils;
import com.iscopy.dailyenglish.utils.AppManager;
import com.iscopy.dailyenglish.widget.SplashTimeCount;
import com.youth.banner.transformer.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SplashActivity  extends BaseActivity {

    @BindView(R.id.vp_image)
    ViewPager vpImage;
    @BindView(R.id.tv_click_time)
    TextView tvClickTime;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.rv_spot)
    RelativeLayout rvSpot;
    /**
     * 页面
     */
    private List<String> listStr;
    private SplashAdapter splashAdapter;

    /**
     * 点
     */
    private List<ImageView> imageViews;

    /**
     * 按钮倒计时
     */
    private SplashTimeCount splashTimeCount;

    public SplashActivity() {
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(View view) {

        splashTimeCount = new SplashTimeCount(6000, 1000, tvClickTime, () -> {
            startActivity(MainActivity.class);
            AppManager.finishActivity(SplashActivity.class);
        });

        listStr = new ArrayList<>();
        listStr.add("http://img3.imgtn.bdimg.com/it/u=2834514051,363432288&fm=26&gp=0.jpg");
        listStr.add("http://img3.imgtn.bdimg.com/it/u=2145854692,2425494519&fm=26&gp=0.jpg");
        listStr.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3511588860,665857437&fm=26&gp=0.jpg");

        if (SharedPreferencesUtils.getBooleanData(DEApplication.getAppContext(), Config.SPLASH_ONE)) {
            listStr.clear();
            listStr.add("https://p0.ssl.qhimgs1.com/sdr/400__/t01c56e30c1fe2590a0.png");
            rvSpot.setVisibility(View.GONE);
            splashTimeCount.start();
        } else {
            SharedPreferencesUtils.saveBooleanData(DEApplication.getAppContext(), Config.SPLASH_ONE, true);
            tvClickTime.setVisibility(View.GONE);
        }

        imageViews = new ArrayList<>();
        for (int i = 0; i < listStr.size(); i++) {
            //设置圆点
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.shape_point1));
            imageViews.add(imageView);
            llContainer.addView(imageView);
            //设置间隔
            if (i != (listStr.size() - 1)) {
                ImageView imageView2 = new ImageView(this);
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.height = 20;
                params.width = 20;
                imageView2.setLayoutParams(params);
                llContainer.addView(imageView2);
            }
        }

        splashAdapter = new SplashAdapter(this, listStr);
        //添加动画效果
        vpImage.setPageTransformer(true, new DepthPageTransformer());
        vpImage.setAdapter(splashAdapter);
        vpImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                for (int j = 0; j < listStr.size(); j++) {
                    imageViews.get(j).setImageDrawable(getResources().getDrawable(R.drawable.shape_point1));
                }
                imageViews.get(i).setImageDrawable(getResources().getDrawable(R.drawable.shape_point2));
            }

            @Override
            public void onPageSelected(int i) {
                if (i == listStr.size() - 1) {
                    tvClickTime.setVisibility(View.VISIBLE);
                    splashTimeCount.start();
                } else {
                    tvClickTime.setVisibility(View.GONE);
                    splashTimeCount.cancel();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        tvClickTime.setOnClickListener(view1 -> {
            startActivity(MainActivity.class);
            splashTimeCount.cancel();
            AppManager.finishActivity(SplashActivity.class);
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
