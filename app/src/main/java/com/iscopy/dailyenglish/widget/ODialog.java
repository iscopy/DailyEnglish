package com.iscopy.dailyenglish.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.iscopy.dailyenglish.R;

public class ODialog extends Dialog {

    public ODialog(@NonNull Context context) {
        super(context);
    }

    public static class Builder {
        private Context context;
        public Builder(Context context) {
            this.context = context;
        }

        public ODialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ODialog dialog = new ODialog(context);
            View view = inflater.inflate(R.layout.item_o_dialog, null);
            dialog.addContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT));
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(view);
            return dialog;
        }
    }


}
