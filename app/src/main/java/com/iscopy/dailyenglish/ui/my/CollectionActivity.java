package com.iscopy.dailyenglish.ui.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.adapter.WordsAdapter;
import com.iscopy.dailyenglish.app.DEApplication;
import com.iscopy.dailyenglish.base.BaseActivity;
import com.iscopy.dailyenglish.constant.Config;
import com.iscopy.dailyenglish.databank.sqlite.WordsDao;
import com.iscopy.dailyenglish.model.Words;
import com.iscopy.dailyenglish.ui.home.WordsActivity;
import com.iscopy.dailyenglish.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的收藏
 */
public class CollectionActivity extends BaseActivity {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    TextView ivRight;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.ll_null_data)
    LinearLayout llNullData;
    @BindView(R.id.rv_word)
    RecyclerView rvWord;

    List<Words> wordsList;
    private WordsAdapter wordsAdapter;

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_collection;
    }

    @Override
    public void initView(View view) {
        tvTitle.setText("我的收藏");
        tvTips.setText("没有收藏，快去学习吧！");
        wordsList = new ArrayList<>();
        wordsAdapter = new WordsAdapter(DEApplication.getAppContext(), wordsList, o -> {
            Words words = wordsList.get((int) o);
            Bundle bundle = new Bundle();
            bundle.putInt("id", words.getId());
            bundle.putString("word", words.getWord());
            bundle.putString("pronunciation", words.getPronunciation());
            bundle.putString("meaning", words.getMeaning());
            bundle.putInt("collection", words.getCollection());
            startActivityForResult(WordsActivity.class, bundle, Config.MY_COLLECTION);
        }, o -> {
            DEApplication.getTts(wordsList.get((int) o).getWord().trim());
        });
        rvWord.setLayoutManager(new GridLayoutManager(DEApplication.getAppContext(), 1));
        rvWord.setAdapter(wordsAdapter);
        rvWord.setNestedScrollingEnabled(false);
        queryData();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick(R.id.iv_return)
    public void onClick() {
        AppManager.finishCurrentActivity();
    }

    public void queryData() {
        //从第page条开始，查询5条
        wordsList.clear();
        wordsList.addAll(WordsDao.queryOrderOut(DEApplication.getDb(), "select * from words where collection=1"));
        if(wordsList.size()==0){
            llNullData.setVisibility(View.VISIBLE);
        }else{
            llNullData.setVisibility(View.GONE);
        }
        wordsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Config.MY_COLLECTION:
                queryData();
                break;
            default:
                break;
        }
    }
}
