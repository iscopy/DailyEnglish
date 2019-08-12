package com.iscopy.dailyenglish.adapter;
/*
 * 作者：iscopy on 2018/10/17
 * 邮箱：iscopy@163.com
 * 版本：v1.0
 * fragment
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
