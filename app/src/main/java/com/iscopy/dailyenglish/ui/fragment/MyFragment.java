package com.iscopy.dailyenglish.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.base.BaseFragment;
import com.iscopy.dailyenglish.ui.my.LoadingActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFragment extends BaseFragment {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.ll_instructions)
    LinearLayout llInstructions;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;
    @BindView(R.id.ll_set_up)
    LinearLayout llSetUp;
    @BindView(R.id.ll_tag)
    LinearLayout llTag;
    @BindView(R.id.tv_editor)
    TextView tvEditor;
    @BindView(R.id.iv_editor)
    ImageView ivEditor;
    @BindView(R.id.ll_collection)
    LinearLayout llCollection;
    @BindView(R.id.ll_sign_in)
    LinearLayout llSignIn;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void receiveRadio(Intent intent) {

    }

    @OnClick({R.id.iv_head,R.id.ll_tag, R.id.iv_editor, R.id.ll_collection, R.id.ll_sign_in,R.id.ll_instructions, R.id.ll_loading, R.id.ll_set_up})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                break;
            case R.id.ll_tag:
                break;
            case R.id.iv_editor:
                break;
            case R.id.ll_collection:
                break;
            case R.id.ll_sign_in:
                break;
            case R.id.ll_instructions:
                break;
            case R.id.ll_loading:
                startActivity(LoadingActivity.class);
                break;
            case R.id.ll_set_up:
                break;
            default:
                break;
        }
    }
}
