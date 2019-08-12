package com.iscopy.dailyenglish.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.utils.GlideUtil;

import java.util.List;

public class SplashAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mData;
    private GlideUtil glideUtil;

    public SplashAdapter(Context context , List<String> list) {
        mContext = context;
        mData = list;
        glideUtil = new GlideUtil();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_splash,null);
        ImageView ivSplash =  view.findViewById(R.id.iv_splash);
        glideUtil.displayImage(mContext, mData.get(position), ivSplash);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
