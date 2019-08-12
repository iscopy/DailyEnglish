package com.iscopy.dailyenglish.ui.home;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.app.DEApplication;
import com.iscopy.dailyenglish.base.BaseActivity;
import com.iscopy.dailyenglish.databank.sqlite.WordsDao;
import com.iscopy.dailyenglish.utils.AppManager;

import butterknife.BindView;
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
    @BindView(R.id.web_word)
    WebView webWord;

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

        if(collection==1){
            ivCollection.setImageResource(R.mipmap.collection);
        }else {
            ivCollection.setImageResource(R.mipmap.give_up);
        }

        WebS();
        webWord.loadUrl("http://www.jukuu.com/search.php?q=" + word);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.iv_return, R.id.iv_collection, R.id.ll_pronunciation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                AppManager.finishCurrentActivity();
                break;
            case R.id.iv_collection:
                ContentValues values = new ContentValues();
                if(collection==1){
                    values.put("collection",0);
                }else {
                    values.put("collection",1);
                }
                int type = WordsDao.updateOrderOut(values, "id=?",new String[]{""+id}, DEApplication.getDb());
                if(type>0){
                    if(collection==1){
                        collection = 0;
                        ivCollection.setImageResource(R.mipmap.give_up);
                    }else {
                        collection = 1;
                        ivCollection.setImageResource(R.mipmap.collection);
                    }
                }
                break;
            case R.id.ll_pronunciation:
                DEApplication.getTts(word);
                break;
            default:
                break;
        }
    }

    @SuppressLint("JavascriptInterface")
    private void WebS() {
        //支持设置
        WebSettings webSettings = webWord.getSettings();
        //允许使用js
        webSettings.setJavaScriptEnabled(true);
        //设置DOM储存
        webSettings.setDomStorageEnabled(false);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //WebView屏幕自适应
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        //支持内容重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //多窗口
        webSettings.supportMultipleWindows();
        //设置可以访问文件
        webSettings.setAllowFileAccess(false);
        //当webview调用requestFocus时为webview设置节点
        webSettings.setNeedInitialFocus(false);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //关闭硬件加速
        webWord.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webWord.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }


}
