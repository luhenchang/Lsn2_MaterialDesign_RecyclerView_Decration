package com.example.ls.lsn2_materialdesign_recyclerview_decration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class RecylerViewGridViewDivider extends RecyclerView.ItemDecoration {
    private int[] attr = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;

    public RecylerViewGridViewDivider(Context context) {
        TypedArray array = context.obtainStyledAttributes(attr);
        mDivider = array.getDrawable(0);
        array.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        onDrawHoriziontal(c, parent);
        onDrawVitical(c, parent);
        super.onDraw(c, parent, state);
    }

    private void onDrawHoriziontal(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams parames = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() - parames.leftMargin;
            int top = child.getBottom() + parames.topMargin;
            int bootom = top + mDivider.getIntrinsicHeight();
            int right = child.getRight() + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bootom);
            mDivider.draw(c);
        }
    }

    private void onDrawVitical(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams parames = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + parames.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            int top = child.getTop() - parames.topMargin;
            int bottom = child.getBottom() + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
        //左边偏移量为0
        int left = 0;
        //上边偏移量为0
        int top = 0;
        //右边偏移量为我们自定义分割线背景图片的宽度
        int right = mDivider.getIntrinsicWidth();
        //下边我们偏移量为我们自定义分割线背景图片的高度。
        int bottom = mDivider.getIntrinsicHeight();
        // outRect.set(left, top, right, bottom);
        //但是我们最后的不要。末尾的也不要。
        if (isLastSpan(itemPosition, parent)) {
            outRect.set(left, top, 0, bottom);
        } else if (isLastRow(itemPosition, parent)) {
            outRect.set(left, top, right, 0);
        } else if (isLastOne(itemPosition, parent)) {
            outRect.set(left, top, right, 0);

        } else {
            outRect.set(left, top, right, bottom);

        }
    }

    private boolean isLastOne(int itemPosition, RecyclerView parent) {
//        ||((itemPosition+1)==itemcount)
        boolean flag = false;
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutmanager = (GridLayoutManager) parent.getLayoutManager();
            int spancount = layoutmanager.getSpanCount();
            int itemcount = parent.getAdapter().getItemCount();
            Log.e("itemposition", itemPosition + "");

            if ((itemPosition + 1) == itemcount) {
                Log.e("itemposition", itemPosition + "");
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    private boolean isLastRow(int itemPosition, RecyclerView parent) {
        boolean flag = false;
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutmanager = (GridLayoutManager) parent.getLayoutManager();
            int spancount = layoutmanager.getSpanCount();
            int itemcount = parent.getAdapter().getItemCount();
            int hancount = itemcount % spancount;//这是剩余的最后一行不需要画线。
            if (itemPosition > itemcount - hancount - 1 && hancount < spancount) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    private boolean isLastSpan(int itemPosition, RecyclerView parent) {
        boolean flag = false;
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutmanager = (GridLayoutManager) parent.getLayoutManager();
            int spancount = layoutmanager.getSpanCount();
            int itemcount = parent.getAdapter().getItemCount();
            if ((itemPosition + 1) % spancount == 0) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }
}
