package com.example.ls.lsn2_materialdesign_recyclerview_decration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 路很长~ on 2017/7/30.
 */

public class GrideItemDividerItemDecoration extends RecyclerView.ItemDecoration {

    private final int[] atrr = new int[]{android.R.attr.listDivider};
    private Drawable mDevider;

    public GrideItemDividerItemDecoration(Context context) {
        TypedArray array = context.obtainStyledAttributes(atrr);
        mDevider = array.getDrawable(0);//获取设置的布局图片
        array.recycle();//回收
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizons(c, parent);
        drawVerticals(c, parent);
        super.onDraw(c, parent, state);

    }

    private void drawHorizons(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) parent.getChildAt(i).getLayoutParams();
            View child = parent.getChildAt(i);
            int left = child.getLeft() - params.leftMargin;
            int top =child.getBottom() + params.topMargin;
            int right = child.getRight() + mDevider.getIntrinsicWidth() + params.rightMargin;
           // int right=child.getWidth()+left+params.rightMargin;
            int bottom = top + mDevider.getIntrinsicHeight();
            mDevider.setBounds(left, top, right, bottom);
            mDevider.draw(c);

        }

    }

    private void drawVerticals(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) parent.getChildAt(i).getLayoutParams();
            View child = parent.getChildAt(i);
            int left = child.getRight() + params.rightMargin;
            int right = left + mDevider.getIntrinsicWidth();
            int top = child.getTop() - params.topMargin;
            int bottom = child.getBottom() + mDevider.getIntrinsicHeight();
            mDevider.setBounds(left, top, right, bottom);
            mDevider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
        int right = mDevider.getIntrinsicWidth();
        int bottom = mDevider.getIntrinsicHeight();
        //设置四个方向的偏移量
        outRect.set(0, 0, right, bottom);
    }

}
