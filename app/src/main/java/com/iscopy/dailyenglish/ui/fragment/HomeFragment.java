package com.iscopy.dailyenglish.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.adapter.WordsAdapter;
import com.iscopy.dailyenglish.app.DEApplication;
import com.iscopy.dailyenglish.base.BaseFragment;
import com.iscopy.dailyenglish.constant.Config;
import com.iscopy.dailyenglish.databank.SharedPreferencesUtils;
import com.iscopy.dailyenglish.databank.sqlite.SignInDao;
import com.iscopy.dailyenglish.databank.sqlite.WordsDao;
import com.iscopy.dailyenglish.interfaces.AParameter;
import com.iscopy.dailyenglish.model.Words;
import com.iscopy.dailyenglish.ui.home.WordsActivity;
import com.iscopy.dailyenglish.ui.my.LoadingActivity;
import com.iscopy.dailyenglish.ui.my.SignInActivity;
import com.iscopy.dailyenglish.utils.AppManager;
import com.iscopy.dailyenglish.utils.L;
import com.iscopy.dailyenglish.utils.T;
import com.iscopy.dailyenglish.widget.IosDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.tv_previous_page)
    TextView tvPreviousPage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_next_page)
    TextView tvNextPage;
    @BindView(R.id.ll_null_data)
    LinearLayout llNullData;
    @BindView(R.id.rv_word)
    RecyclerView rvWord;

    List<Words> wordsList;
    private WordsAdapter wordsAdapter;
    private int page;
    private int pages;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        pages = queryDay();
        tvTitle.setText("已学习" + pages + "天");
        if(pages==0){
            page = pages;
        }else{
            page = pages*5;
        }
        wordsList = new ArrayList<>();
        wordsAdapter = new WordsAdapter(DEApplication.getAppContext(), wordsList, o -> {
            Words words = wordsList.get((int) o);
            Bundle bundle = new Bundle();
            bundle.putInt("id", words.getId());
            bundle.putString("word", words.getWord());
            bundle.putString("pronunciation", words.getPronunciation());
            bundle.putString("meaning", words.getMeaning());
            bundle.putInt("collection", words.getCollection());
            startActivityForResult(WordsActivity.class, bundle, Config.WORD_COLLECTION);
        }, o -> {
            DEApplication.getTts(wordsList.get((int) o).getWord().trim());
        });
        rvWord.setLayoutManager(new GridLayoutManager(DEApplication.getAppContext(), 1));
        rvWord.setAdapter(wordsAdapter);
        rvWord.setNestedScrollingEnabled(false);
        queryData(page);
    }


    public void queryData(int page) {
        //从第page条开始，查询5条
        wordsList.clear();
        wordsList.addAll(WordsDao.queryOrderOut(DEApplication.getDb(), "select * from words id limit " + page + ",5"));
        if(wordsList.size()==0){
            llNullData.setVisibility(View.VISIBLE);
        }else{
            llNullData.setVisibility(View.GONE);
        }
        wordsAdapter.notifyDataSetChanged();
    }

    /**
     * 查询签到次数（签到了多少天了）
     * @return
     */
    public int queryDay() {
        try{
            return SignInDao.queryOrderOut(DEApplication.getDb(), "select * from signin").size();
        }catch (NullPointerException e){
            return 0;
        }

    }

    @Override
    public void receiveRadio(Intent intent) {

    }

    @OnClick({R.id.tv_previous_page, R.id.tv_next_page, R.id.ll_null_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_previous_page:
                if ((page - 5) < 0) {
                    IosDialog.Builder builder = new IosDialog.Builder(getMContext());
                    builder.setMessage("当前页面已经是第一页了！");
                    builder.setPositiveButton("继续学习", (dialogInterface, i) -> dialogInterface.dismiss());
                    builder.create().show();
                } else {
                    page -= 5;
                    queryData(page);
                }
                break;
            case R.id.tv_next_page:
                if ((page + 5) > 2000) {
                    IosDialog.Builder builder = new IosDialog.Builder(getMContext());
                    builder.setMessage("恭喜你，学完了本阶段所有单词，请耐心等待下一版本发布！");
                    builder.setPositiveButton("继续学习", (dialogInterface, i) -> dialogInterface.dismiss());
                    builder.create().show();
                } else {
                    if(page==(pages*5)){
                        IosDialog.Builder builder = new IosDialog.Builder(getMContext());
                        builder.setMessage("已是最新页了，如果学完本页记得打卡哦？");
                        builder.setPositiveButton("继续学习", (dialogInterface, i) -> dialogInterface.dismiss());
                        builder.setNegativeButton("前去打卡", (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                            //前去打卡签到
                            startActivity(SignInActivity.class, Config.CLOCK_IN);
                        });
                        builder.create().show();
                    }else{
                        page += 5;
                        queryData(page);
                    }
                }
                break;
            case R.id.ll_null_data:
                startActivity(LoadingActivity.class, Config.WORD_LOADING);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Config.WORD_COLLECTION:
                queryData(page);
                break;
            case Config.WORD_LOADING:
                queryData(page);
                break;
            case Config.CLOCK_IN:
                tvTitle.setText("已学习" + queryDay() + "天");
                break;
            default:
                break;
        }
    }
}
