/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/27/19 9:11 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/27/19 8:57 PM
 *
 */

package com.volunteerx.app.VolunteerXDialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.volunteerx.app.R;


public class VolunteerXDialog {
    public static class Builder {

        private static final String TAG = "Builder";

        private String title, message, positiveBtnText, negativeBtnText, pBtnColor, nBtnColor, pBtnTextColor, nBtnTextColor;
        private Activity activity;
        private VolunteerXDialogListener pListener, nListener;
        private boolean cancel;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setPositiveBtnText(String positiveBtnText) {
            this.positiveBtnText = positiveBtnText;
            return this;
        }

        public Builder setNegativeBtnText(String negativeBtnText) {
            this.negativeBtnText = negativeBtnText;
            return this;
        }

        public Builder setnBtnColor(String nBtnColor) {
            this.nBtnColor = nBtnColor;
            return this;
        }

        public Builder setpBtnColor(String pBtnColor) {
            this.pBtnColor = pBtnColor;
            return this;
        }

        public Builder setpBtnTextColor(String pBtnTextColor) {
            this.pBtnTextColor = pBtnTextColor;
            return this;
        }

        public Builder setnBtnTextColor(String nBtnTextColor) {
            this.nBtnTextColor = nBtnTextColor;
            return this;
        }

        public Builder OnPositiveClicked(VolunteerXDialogListener pListener) {
            this.pListener = pListener;
            return this;
        }

        public Builder OnNegativeClicked(VolunteerXDialogListener nListener) {
            this.nListener = nListener;
            return this;
        }

        public Builder isCancellable(boolean cancel) {
            this.cancel = cancel;
            return this;
        }

        public VolunteerXDialog build() {

            TextView message1, title1;
            Button nButton, pButton;
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(cancel);
            dialog.setContentView(R.layout.dialog_layout);

            title1 = dialog.findViewById(R.id.title);
            message1 = dialog.findViewById(R.id.message);
            nButton = dialog.findViewById(R.id.negativeBtn);
            pButton = dialog.findViewById(R.id.positiveBtn);

            title1.setText(title);
            message1.setText(message);

            if (positiveBtnText != null) {
                pButton.setText(positiveBtnText);
                if (pBtnColor != null) {
                    GradientDrawable bgShape = (GradientDrawable) pButton.getBackground();
                    bgShape.setColor(Color.parseColor(pBtnColor));
                }
                if (pBtnTextColor != null) pButton.setTextColor(Color.parseColor(pBtnTextColor));
                pButton.setOnClickListener((View view)-> {
                    if (pListener != null) pListener.OnClick(dialog);
                    dialog.dismiss();
                });
            }else pButton.setVisibility(View.GONE);

            if (negativeBtnText != null) {
                nButton.setText(negativeBtnText);
                if (nBtnColor != null) {
                    GradientDrawable bgShape = (GradientDrawable) pButton.getBackground();
                    bgShape.setColor(Color.parseColor(nBtnColor));
                }
                if (nBtnTextColor != null) pButton.setTextColor(Color.parseColor(nBtnTextColor));
                nButton.setOnClickListener((View view)-> {
                    if (nListener != null) nListener.OnClick(dialog);
                    dialog.dismiss();
                });
            }else nButton.setVisibility(View.GONE);

            dialog.show();

            return new VolunteerXDialog();
        }

    }

    public interface VolunteerXDialogListener {

        void OnClick(Dialog dialog);
    }


}