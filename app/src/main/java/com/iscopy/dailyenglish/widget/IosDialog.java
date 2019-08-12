package com.iscopy.dailyenglish.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;

public class IosDialog extends Dialog {

    public IosDialog(@NonNull Context context) {
        super(context);
    }

    public static class Builder {

        private Context context;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public IosDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final IosDialog dialog = new IosDialog(context);
            View view = inflater.inflate(R.layout.item_ios_dialog, null);
            dialog.addContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT));
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView ios_dialog_message = view.findViewById(R.id.ios_dialog_message);
            TextView ios_dialog_left = view.findViewById(R.id.ios_dialog_left);
            TextView ios_dialog_right = view.findViewById(R.id.ios_dialog_right);
            LinearLayout ll_line = view.findViewById(R.id.ll_line);

            if(positiveButtonText != null){
                ios_dialog_left.setText(positiveButtonText);
                if(positiveButtonClickListener != null){
                    ios_dialog_left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            }else{
                ios_dialog_left.setVisibility(View.GONE);
                ll_line.setVisibility(View.GONE);
            }

            if(negativeButtonText != null){
                ios_dialog_right.setText(negativeButtonText);
                if(negativeButtonClickListener != null){
                    ios_dialog_right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            }else{
                ios_dialog_right.setVisibility(View.GONE);
                ll_line.setVisibility(View.GONE);
            }

            if(message != null){
                ios_dialog_message.setText(message);
            }

            dialog.setContentView(view);

            return dialog;
        }
    }
}

