/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/12/20 9:19 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/12/20 9:19 PM
 *
 */

package com.volunteerx.app.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import mva2.adapter.MultiViewAdapter;
import mva2.adapter.decorator.Decorator;

public class DividerDecorator extends Decorator {

    private final Rect bounds = new Rect();
    private final Drawable divider;

    public DividerDecorator(MultiViewAdapter adapter, Context context) {
        super(adapter);
        int[] attrs = new int[] { android.R.attr.listDivider };
        final TypedArray a = context.obtainStyledAttributes(attrs);
        divider = a.getDrawable(0);
        a.recycle();
    }

    @Override public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                         @NonNull RecyclerView parent, @NonNull RecyclerView.State state, int adapterPosition) {
        int itemPositionType = getPositionType(adapterPosition, parent);
        if (isFirst(itemPositionType)) {
            addToRect(outRect, 0, 0, 0, divider.getIntrinsicHeight());
        }else return;
    }

    @Override public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent,
                                 @NonNull RecyclerView.State state, View child, int adapterPosition) {
        if (parent.getLayoutManager() == null) {
            return;
        }
        if (isFirst(getPositionType(adapterPosition, parent))) {
            drawVertical(canvas, parent, child);
        }
        else return;

    }

    @SuppressLint("NewApi")
    private void drawVertical(Canvas canvas, RecyclerView parent, View child) {
        canvas.save();
        final int left;
        final int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        parent.getDecoratedBoundsWithMargins(child, bounds);
        final int bottom = bounds.bottom + Math.round(ViewCompat.getTranslationY(child));
        final int top = bottom - divider.getIntrinsicHeight();
        divider.setBounds(left, top, right, bottom);
        divider.draw(canvas);
        canvas.restore();
    }
}