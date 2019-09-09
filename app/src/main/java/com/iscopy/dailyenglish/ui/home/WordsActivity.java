package com.iscopy.dailyenglish.ui.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.adapter.SentenceAdapter;
import com.iscopy.dailyenglish.app.DEApplication;
import com.iscopy.dailyenglish.base.BaseActivity;
import com.iscopy.dailyenglish.constant.Config;
import com.iscopy.dailyenglish.databank.sqlite.SentenceDao;
import com.iscopy.dailyenglish.databank.sqlite.WordsDao;
import com.iscopy.dailyenglish.model.Sentence;
import com.iscopy.dailyenglish.ui.my.LoadingActivity;
import com.iscopy.dailyenglish.utils.AppManager;
import com.iscopy.dailyenglish.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WordsActivity extends BaseActivity {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    TextView ivRight;
    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.iv_collection)
    ImageView ivCollection;
    @BindView(R.id.tv_pronunciation)
    TextView tvPronunciation;
    @BindView(R.id.ll_pronunciation)
    LinearLayout llPronunciation;
    @BindView(R.id.tv_meaning)
    TextView tvMeaning;
    @BindView(R.id.rv_sentence)
    RecyclerView rvSentence;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.ll_null_data)
    LinearLayout llNullData;

    private int id;
    /**
     * 单词
     */
    private String word;
    /**
     * 发音
     */
    private String pronunciation;
    /**
     * 含义
     */
    private String meaning;
    /**
     * 收藏
     */
    private int collection;

    /**
     * 造句
     */
    private SentenceAdapter sentenceAdapter;
    private List<Sentence> sentenceList;


    @Override
    public void initParms(Bundle parms) {
        id = parms.getInt("id");
        word = parms.getString("word");
        pronunciation = parms.getString("pronunciation");
        meaning = parms.getString("meaning");
        collection = parms.getInt("collection");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_words;
    }

    @Override
    public void initView(View view) {
        tvTitle.setText("单词详情");
        tvWord.setText(word);
        tvPronunciation.setText(pronunciation);
        tvMeaning.setText(meaning);

        if (collection == 1) {
            ivCollection.setImageResource(R.mipmap.collection);
        } else {
            ivCollection.setImageResource(R.mipmap.give_up);
        }

        tvTips.setText("单词造句数据为空，请点击去加载页加载");

        sentenceList = new ArrayList<>();
        sentenceAdapter = new SentenceAdapter(getMContext(), sentenceList, o -> {
            DEApplication.getTts(sentenceList.get((int)o).getSentence());
        });
        rvSentence.setLayoutManager(new GridLayoutManager(getMContext(), 1));
        rvSentence.setAdapter(sentenceAdapter);
        rvSentence.setNestedScrollingEnabled(false);

        try{
            getSentence(word);
        }catch (SQLiteException e){
            llNullData.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.iv_return, R.id.iv_collection, R.id.ll_pronunciation, R.id.ll_null_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                AppManager.finishCurrentActivity();
                break;
            case R.id.iv_collection:
                ContentValues values = new ContentValues();
                if (collection == 1) {
                    values.put("collection", 0);
                } else {
                    values.put("collection", 1);
                }
                int type = WordsDao.updateOrderOut(values, "id=?", new String[]{"" + id}, DEApplication.getDb());
                if (type > 0) {
                    if (collection == 1) {
                        collection = 0;
                        ivCollection.setImageResource(R.mipmap.give_up);
                        T.showShort("取消收藏成功");
                    } else {
                        collection = 1;
                        ivCollection.setImageResource(R.mipmap.collection);
                        T.showShort("收藏成功");
                    }
                }
                break;
            case R.id.ll_pronunciation:
                DEApplication.getTts(word);
                break;
            case R.id.ll_null_data:
                startActivity(LoadingActivity.class, Config.WORD_LOADING);
                break;
            default:
                break;
        }
    }

    public void getSentence(String words){
        String sql = "select * from sentence where word=\'" + words + "\'";
        List<Sentence> sentenceListSql = SentenceDao.queryOrderOut(DEApplication.getDb(), sql);
        sentenceList.addAll(sentenceListSql);
        if (sentenceList.size() != 0) {
            sentenceAdapter.notifyDataSetChanged();
            llNullData.setVisibility(View.GONE);
        } else {
            llNullData.setVisibility(View.VISIBLE);
        }
    }
}
