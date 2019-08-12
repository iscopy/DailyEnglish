package com.iscopy.dailyenglish.ui.my;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.app.DEApplication;
import com.iscopy.dailyenglish.base.BaseActivity;
import com.iscopy.dailyenglish.databank.sqlite.WordsDao;
import com.iscopy.dailyenglish.model.Words;
import com.iscopy.dailyenglish.utils.AppManager;
import com.iscopy.dailyenglish.utils.L;
import com.iscopy.dailyenglish.utils.T;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoadingActivity extends BaseActivity {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_get_data)
    Button btnGetData;

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_loading;
    }

    @Override
    public void initView(View view) {
        tvTitle.setText("加载数据");
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.iv_return, R.id.btn_get_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                AppManager.finishActivity(LoadingActivity.class);
                break;
            case R.id.btn_get_data:
                WordsDao.deleteOrderOut(DEApplication.getDb());
                List<String> list = new ArrayList<>();
                try {
                    list.addAll(getFileContext());
                } catch (Exception e) {
                    e.printStackTrace();
                    T.showShort("解析文件失败！请稍后再试");
                }
                for (int i = 0; i < list.size(); i++) {
                    int id = Integer.parseInt(specification(list.get(i).substring(0, list.get(i).indexOf(" "))));
                    String word = specification(list.get(i).substring(list.get(i).indexOf(" "), list.get(i).indexOf("[")));
                    String pronunciation = specification(list.get(i).substring(list.get(i).indexOf("["), list.get(i).indexOf("]") + 1));
                    String meaning = specification(list.get(i).substring(list.get(i).indexOf("]") + 1, list.get(i).length()));
                    Words words = new Words(id, word, pronunciation, meaning, 0);
                    WordsDao.insertOrderOut(words, DEApplication.getDb());
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取txt文件内容并按行放入list中
     */
    public List<String> getFileContext() throws Exception {
        InputStream inputStream = getResources().openRawResource(R.raw.this2000word);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader buffer = new BufferedReader(inputStreamReader);
        List<String> list = new ArrayList<String>();
        String str = null;
        while ((str = buffer.readLine()) != null) {
            if (str.trim().length() > 2) {
                list.add(str);
            }
        }
        return list;
    }

    private String specification(String str) {
        str = str.trim();
        //这里判断是不是全角空格
        while (str.startsWith("　")) {
            str = str.substring(1, str.length()).trim();
        }
        while (str.endsWith("　")) {
            str = str.substring(0, str.length() - 1).trim();
        }
        return str;
    }
}
