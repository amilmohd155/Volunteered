/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/29/19 11:58 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/28/19 11:30 PM
 *
 */

package com.volunteerx.app.SimpleProgressDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.volunteerx.app.R;

public class ProgressDialog {

    public static final int LIGHT_THEME = 1001;
    public static final int DARK_THEME = 1002;
    public static final int CUSTOM_THEME = 1003;

    public ProgressDialog() {
    }

    public static class Builder{

        private Context context;
        private String message;
        private int theme;


        private TextView tvMessage;
        private ProgressBar progressBar;
        private CardView cardView;
        private Dialog dialog;
        private int messageColor, backgroundColor, progressColor;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTheme(int theme) {
            this.theme = theme;
            return this;
        }

        public Builder setMessageColor(int messageColor) {
            this.messageColor = messageColor;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setProgressColor(int progressColor) {
            this.progressColor = progressColor;
            return this;
        }

         public void dismiss() {
            dialog.dismiss();
         }

        public ProgressDialog build() {

            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.layout_progress_dialog);

            tvMessage = dialog.findViewById(R.id.message);
            progressBar = dialog.findViewById(R.id.loader);
            cardView = dialog.findViewById(R.id.card_view);

            if (theme != -1) initTheme();
            else {
                if (messageColor != -1) tvMessage.setTextColor(context.getColor(messageColor));
                if (backgroundColor != -1) cardView.setCardBackgroundColor(backgroundColor);
                if (progressColor != -1) progressBar.setIndeterminateTintList(ColorStateList.valueOf(context.getColor(progressColor)));
            }

            tvMessage.setText(message);

            dialog.show();

            return new ProgressDialog();

        }

        private void initTheme() {
            switch (theme) {
                case DARK_THEME:
                    cardView.setCardBackgroundColor(context.getColor(R.color.colorBlack));
                    progressBar.setIndeterminateTintList(ColorStateList.valueOf(context.getColor(R.color.colorWhite)));
                    tvMessage.setTextColor(context.getColor(R.color.colorWhite));
                    break;
                case LIGHT_THEME:
                    cardView.setCardBackgroundColor(context.getColor(R.color.colorWhite));
                    progressBar.setIndeterminateTintList(ColorStateList.valueOf(context.getColor(R.color.colorBlack)));
                    tvMessage.setTextColor(context.getColor(R.color.colorBlack));
            }
        }

    }

}
