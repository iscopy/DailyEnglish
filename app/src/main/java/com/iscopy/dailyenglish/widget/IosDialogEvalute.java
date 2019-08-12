package com.iscopy.dailyenglish.widget;
/*
 * 作者：iscopy on 2018/10/30
 * 邮箱：iscopy@163.com
 * 版本：v1.0
 */

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;

public class IosDialogEvalute extends Dialog {

    public IosDialogEvalute(@NonNull Context context) {
        super(context);
    }

    public static class Builder {

        private Context context;
        private String positiveButtonText;
        private String negativeButtonText;
        private String cancelButtonText;
        private DialogInterface.OnClickListener cancelButtonClickListener;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public IosDialogEvalute.Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public IosDialogEvalute.Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public IosDialogEvalute.Builder setCancelButton(String cancelButtonText, DialogInterface.OnClickListener listener) {
            this.cancelButtonText = cancelButtonText;
            this.cancelButtonClickListener = listener;
            return this;
        }

        public IosDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final IosDialog dialog = new IosDialog(context);
            View view = inflater.inflate(R.layout.item_ios_dialog_image, null);
            //位置
            Window dialogWindow = dialog.getWindow();
            //WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            dialogWindow.setGravity(Gravity.BOTTOM);
            dialog.addContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT));
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView evaluate_phone = view.findViewById(R.id.evaluate_phone);
            TextView evaluate_Album = view.findViewById(R.id.evaluate_Album);
            TextView evaluate_cancel = view.findViewById(R.id.evaluate_cancel);

            if(positiveButtonText != null){
                evaluate_phone.setText(positiveButtonText);
                if(positiveButtonClickListener != null){
                    evaluate_phone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            }else{
                evaluate_phone.setVisibility(View.GONE);
            }

            if(negativeButtonText != null){
                evaluate_Album.setText(negativeButtonText);
                if(negativeButtonClickListener != null){
                    evaluate_Album.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            }else{
                evaluate_Album.setVisibility(View.GONE);
            }

            if(cancelButtonText != null){
                evaluate_cancel.setText(cancelButtonText);
                if(cancelButtonClickListener != null){
                    evaluate_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cancelButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            }else{
                evaluate_Album.setVisibility(View.GONE);
            }

            dialog.setContentView(view);

            return dialog;
        }
    }

}
