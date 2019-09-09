package com.iscopy.dailyenglish.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.interfaces.AParameter;
import com.iscopy.dailyenglish.model.Sentence;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.ControlsView> {

    private Context context;
    private List<Sentence> sentenceList;
    private AParameter aParameter;
    public SentenceAdapter(Context context, List<Sentence> sentenceList, AParameter aParameter) {
        this.context = context;
        this.sentenceList = sentenceList;
        this.aParameter = aParameter;
    }

    @NonNull
    @Override
    public ControlsView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sentence, null, false);
        return new ControlsView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ControlsView controlsView, int i) {
        controlsView.llView.setOnClickListener(view -> {
            aParameter.aParameter(i);
        });
        controlsView.tvSentence.setText(specification(sentenceList.get(i).getSentence()));
        controlsView.tvSentence2.setText(sentenceList.get(i).getSentence2());
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

    @Override
    public int getItemCount() {
        return sentenceList.size();
    }

    class ControlsView extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_sentence)
        TextView tvSentence;
        @BindView(R.id.tv_sentence2)
        TextView tvSentence2;
        @BindView(R.id.ll_view)
        LinearLayout llView;

        public ControlsView(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
