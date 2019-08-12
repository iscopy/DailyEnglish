package com.iscopy.dailyenglish.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.interfaces.AParameter;
import com.iscopy.dailyenglish.model.Words;
import com.iscopy.dailyenglish.ui.MainActivity;
import com.iscopy.dailyenglish.utils.L;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ControlsView> {

    private Context context;
    private List<Words> wordsList;
    private AParameter aParameter;
    private AParameter aParameter2;

    public WordsAdapter(Context context, List<Words> wordsList, AParameter aParameter, AParameter aParameter2) {
        this.context = context;
        this.wordsList = wordsList;
        this.aParameter = aParameter;
        this.aParameter2 = aParameter2;
    }

    @NonNull
    @Override
    public ControlsView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_words, null, false);
        return new ControlsView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ControlsView controlsView, int i) {
        controlsView.tvWord.setText(wordsList.get(i).getWord());
        controlsView.tvPronunciation.setText(wordsList.get(i).getPronunciation());
        controlsView.tvMeaning.setText(wordsList.get(i).getMeaning());

        controlsView.llCard.setOnClickListener(view -> {
            aParameter.aParameter(i);
        });

        controlsView.llPronunciation.setOnClickListener(view -> {
            aParameter2.aParameter(i);
        });

    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }

    class ControlsView extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_card)
        LinearLayout llCard;
        @BindView(R.id.tv_word)
        TextView tvWord;
        @BindView(R.id.tv_pronunciation)
        TextView tvPronunciation;
        @BindView(R.id.ll_pronunciation)
        LinearLayout llPronunciation;
        @BindView(R.id.tv_meaning)
        TextView tvMeaning;

        public ControlsView(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
