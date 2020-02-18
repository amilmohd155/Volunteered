/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/13/20 8:10 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/13/20 8:10 PM
 *
 */

package com.volunteerx.app.profile;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.volunteerx.app.R;

import mva2.adapter.MultiViewAdapter;
import mva2.adapter.decorator.Decorator;
import mva2.adapter.decorator.SectionPositionType;

import static mva2.adapter.decorator.SectionPositionType.FIRST;
import static mva2.adapter.decorator.SectionPositionType.LAST;

public class SectionDecorator extends Decorator {

    private final Rect mBounds = new Rect();
    private final int offsetInPixels;
    private Paint dividerPaint = new Paint();

    public SectionDecorator(MultiViewAdapter adapter, Context context, int dp) {
        super(adapter);
        int color = ContextCompat.getColor(context, R.color.grey_500);
        dividerPaint.setColor(color);
        offsetInPixels = dp;
    }

//    @Override public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent,
//                                 @NonNull RecyclerView.State state, View child, int adapterPosition) {
//        SectionPositionType sectionPositionType = getSectionPositionType(adapterPosition);
//        int positionType = getPositionType(adapterPosition, parent);
//        if (!(sectionPositionType == FIRST && isFirst(positionType)) && !isLast(positionType)) {
//            return;
//        }
//
//        canvas.save();
//        final int left;
//        final int right;
//        if (parent.getClipToPadding()) {
//            left = parent.getPaddingLeft();
//            right = parent.getWidth() - parent.getPaddingRight();
//            canvas.clipRect(left, parent.getPaddingTop(), right,
//                    parent.getHeight() - parent.getPaddingBottom());
//        } else {
//            left = 0;
//            right = parent.getWidth();
//        }
//        parent.getDecoratedBoundsWithMargins(child, mBounds);
//
//        if (isLast(positionType)) {
//            final int bottom = mBounds.bottom + Math.round(child.getTranslationY()) - 1;
//            final int top = bottom - offsetInPixels + 1;
//
//            canvas.drawLine(left, top, right, top, dividerPaint);
//            if (getSectionPositionType(adapterPosition) != LAST) {
//                canvas.drawLine(left, bottom, right, bottom, dividerPaint);
//            }
//        } else {
//            final int top = mBounds.top;
//            final int bottom = top + offsetInPixels - 1;
//
//            canvas.drawLine(left, bottom, right, bottom, dividerPaint);
//        }
//        canvas.restore();
//    }

    @Override
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state, int adapterPosition) {
//        SectionPositionType sectionPositionType = getSectionPositionType(adapterPosition);
        int positionType = getPositionType(adapterPosition, recyclerView);
//        if (!(sectionPositionType == FIRST && isFirst(positionType)) && !isLast(positionType)) {
//            return;
//        }
        if (isLast(positionType)) {
            rect.top = offsetInPixels;
            rect.bottom = offsetInPixels;
        }else  {
            rect.top = offsetInPixels;
        }
//        if (isLast(positionType)) {
//            addToRect(rect, 0, 0, 0, offsetInPixels);
//        } else {
//            addToRect(rect, 0, 100, 0, 0);
//        }

    }
}