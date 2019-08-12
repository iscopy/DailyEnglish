package com.iscopy.dailyenglish.ui.my;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.app.DEApplication;
import com.iscopy.dailyenglish.base.BaseActivity;
import com.iscopy.dailyenglish.constant.Config;
import com.iscopy.dailyenglish.databank.SharedPreferencesUtils;
import com.iscopy.dailyenglish.utils.AppManager;
import com.iscopy.dailyenglish.utils.L;
import com.iscopy.dailyenglish.utils.T;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑我的金句
 */
public class EditorActivity extends BaseActivity {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    TextView ivRight;
    @BindView(R.id.et_quotes)
    EditText etQuotes;
    @BindView(R.id.tv_quotes)
    TextView tvQuotes;
    @BindView(R.id.btn_quotes)
    Button btnQuotes;

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_editor;
    }

    @Override
    public void initView(View view) {
        //输入监听
        etQuotes.addTextChangedListener(new TextWatcher() {
            @Override
            //s 更改前的文字、 start 有变动的字符串的序号、 count 被改变的长度（如果是增加，则为0）、 after 被修改的文字的长度（加了几个）如果是删除的话则为0
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                L.d("etListener1", s + " " + start + " " + count + " " + after);
            }

            @Override
            //s 更改后的文字、 start 有变动的字符串的序号、 before 被改变的长度（如果是增加，则为0）、 count 被修改的文字的长度（加了几个）如果是删除的话则为0
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                L.d("etListener2", s + " " + start + " " + before + " " + count);
            }

            @Override//s 修改后的文字
            public void afterTextChanged(Editable s) {
                L.d("etListener3", s + "");
                if (s.length() > 50) {
                    etQuotes.setText(s.toString().substring(0, 50));
                    tvQuotes.setText(etQuotes.getText().toString().length() + "/50");
                } else {
                    tvQuotes.setText(s.length() + "/50");
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.iv_return, R.id.btn_quotes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                AppManager.finishCurrentActivity();
                break;
            case R.id.btn_quotes:
                boolean trfa = SharedPreferencesUtils.saveStringData(DEApplication.getAppContext(), Config.MY_QUOTES, etQuotes.getText().toString());
                if(trfa){
                    T.showShort("保存成功");
                    AppManager.finishCurrentActivity();
                }else{
                    T.showShort("保存失败");
                }
                break;
            default:
                break;
        }
    }
}
