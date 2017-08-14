package com.example.ls.lsn2_materialdesign_recyclerview_decration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 路很长~ on 2017/7/30.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private final int[] attrs = new int[]{
            android.R.attr.listDivider
    };
    private int mOrientaion = LinearLayoutManager.VERTICAL;
    private int bottom;
    private Drawable mDivider;

    public DividerItemDecoration(Context context, int orientation) {
        TypedArray typearray = context.obtainStyledAttributes(attrs);
        mDivider = typearray.getDrawable(0);
        typearray.recycle();//缓存数组对象到内存中，不需要每次都需要创建加载。
        setOrientation(orientation);


    }

    public void setOrientation(int orientation) {
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("哥们！你逗我么？，水平和线下枚举类型");
        }
        this.mOrientaion = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //2.调用这个方法，RecyclerView会毁掉该绘制方法，需要自己绘制条目的间隔线
        if(mOrientaion==LinearLayoutManager.VERTICAL){//垂直
            drawVertical(c,parent);
        }else{
            drawHorizontal(c,parent);
        }

        super.onDraw(c, parent, state);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount ; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top , right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        // 画水平线
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount ; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top , right, bottom);
            mDivider.draw(c);
        }
    }
    
    /**
     * @param outRect 矩形区域
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //1.调用侧方法获取条目的偏移量（首先会获取条目直接的宽度--Rect:矩形区域）
        //获得条目的偏移量（所有的条目都会调用一次方法）
        if (mOrientaion == LinearLayoutManager.VERTICAL) {//垂直
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {//水平
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
        super.getItemOffsets(outRect, view, parent, state);

    }
}
