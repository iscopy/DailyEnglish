package com.iscopy.dailyenglish.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.model.SignIn;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignDateAdapter extends RecyclerView.Adapter<SignDateAdapter.ControlsView> {

    private Context context;
    /**
     * 签到日志
     */
    private List<SignIn> signInList;
    /**
     * 日期数据，包括星期
     */
    private List<String> stringList;
    public SignDateAdapter(Context context, List<SignIn> signInList, List<String> stringList) {
        this.context = context;
        this.signInList = signInList;
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public ControlsView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sign_date, null, false);
        return new ControlsView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ControlsView controlsView, int i) {
        controlsView.tvWeek.setText(stringList.get(i));
        if(stringList.get(i).equals("0")){
            controlsView.tvWeek.setVisibility(View.GONE);
        }
        if(i>6){
            for(int j=0;j<signInList.size();j++){
                if(signInList.get(j).getDay() == Integer.parseInt(stringList.get(i))){
                    controlsView.tvWeek.setTextColor(Color.parseColor("#FD0000"));
                    controlsView.ivStatus.setVisibility(View.VISIBLE);
                }else{
                    controlsView.tvWeek.setTextColor(Color.parseColor("#666666"));
                    controlsView.ivStatus.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class ControlsView extends RecyclerView.ViewHolder {

        @BindView(R.id.tvWeek)
        TextView tvWeek;
        @BindView(R.id.ivStatus)
        ImageView ivStatus;
        @BindView(R.id.rlItem)
        RelativeLayout rlItem;

        public ControlsView(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
