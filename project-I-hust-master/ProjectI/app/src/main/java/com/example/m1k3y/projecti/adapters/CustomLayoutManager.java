package com.example.m1k3y.projecti.adapters;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

public class CustomLayoutManager extends GridLayoutManager {

    private static final float MILLISECONDS_PER_INCH = 0.1f;
    private int mPendingTargetPos = -1;
    private int mPendingPosOffset = -1;

    public CustomLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public CustomLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        if(recyclerView == null) return ;
        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {

            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return super.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
            }
        };

        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (mPendingTargetPos != -1 && state.getItemCount() > 0) {
            /*
            Data is present now, we can set the real scroll position
            */
            scrollToPositionWithOffset(mPendingTargetPos, mPendingPosOffset);
            mPendingTargetPos = -1;
            mPendingPosOffset = -1;
        }
        super.onLayoutChildren(recycler, state);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        mPendingTargetPos = -1;
        mPendingPosOffset = -1;
        super.onRestoreInstanceState(state);
    }

    public void setTargetStartPos(int position, int offset) {
        mPendingTargetPos = position;
        mPendingPosOffset = offset;
    }
}
