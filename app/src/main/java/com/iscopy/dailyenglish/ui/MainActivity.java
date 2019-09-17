package com.iscopy.dailyenglish.ui;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.adapter.FragmentAdapter;
import com.iscopy.dailyenglish.base.BaseActivity;
import com.iscopy.dailyenglish.ui.fragment.HomeFragment;
import com.iscopy.dailyenglish.ui.fragment.MyFragment;
import com.iscopy.dailyenglish.widget.NoSlidingViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_fragment)
    NoSlidingViewPager vpFragment;
    @BindView(R.id.tl_menu)
    TabLayout tlMenu;

    /**
     * Tab 文字
     * R.string.home, R.string.proposal, R.string.my
     */
    private final int[] tabTitle = new int[]{R.string.home, R.string.my};
    /**
     * Tab 图片
     * R.drawable.boolean_home, R.drawable.boolean_proposal, R.drawable.boolean_my
     */
    private final int[] tabImage = new int[]{R.drawable.boolean_home, R.drawable.boolean_my};

    /**
     * Fragment集合
     */
    private List<Fragment> fragments;

    private FragmentAdapter fragmentAdapter;

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        setTabs(tlMenu, this.getLayoutInflater(), tabTitle, tabImage);
        pageLoad();
        binding();
    }

    private void binding() {
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        vpFragment.setAdapter(fragmentAdapter);
        vpFragment.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMenu));
        tlMenu.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vpFragment));
    }

    /**
     * 添加Fragment
     */
    private void pageLoad() {
        fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        MyFragment myFragment = new MyFragment();
        fragments.add(homeFragment);
        fragments.add(myFragment);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    /**
     * @description: 设置添加Tab
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitlees, int[] tabImgs) {
        for (int i = 0; i < tabImgs.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_home_menu, null);
            TextView tvTitle = view.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitlees[i]);
            ImageView imgTab = view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);
            tab.setCustomView(view);
        }
    }

    /**
     * 记录用户首次点击返回键的时间
     */
    private long firstTime = 0;
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            super.onBackPressed();
        }

    }
}