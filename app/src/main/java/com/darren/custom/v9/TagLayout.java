package com.darren.custom.v9;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * date  1/18/21  9:39 PM
 * author  DarrenHang
 */
public class TagLayout extends ViewGroup {

    private List<List<View>> mChildViews = new ArrayList<>();

    public TagLayout(Context context) {
        this(context, null);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mChildViews.clear();
        //获取子View的个数
        int childCount = getChildCount();
        //父View的宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //需要计算的高度
        int height = getPaddingTop() + getPaddingBottom();
        //单行的宽度
        int lineWidth = getPaddingLeft();
        //存放一行的View
        List<View> childViews = new ArrayList<>();
        mChildViews.add(childViews);
        //处理单行不换行情况
        int maxHeight = 0;
        //循环测量子View
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //测量子View的宽高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //获取子View的 margin
            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
            //根据子View的宽度来处理换行
            if (lineWidth + (childView.getMeasuredWidth() + params.leftMargin + params.rightMargin) > width) {
                //换行
                height += maxHeight;
                lineWidth = childView.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                childViews = new ArrayList<>();
                mChildViews.add(childViews);
            } else {
                //累加宽度
                lineWidth += childView.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                maxHeight = Math.max(childView.getMeasuredHeight() + params.topMargin + params.bottomMargin, maxHeight);
            }
            childViews.add(childView);

        }

        height += maxHeight;
        //设置宽高
        setMeasuredDimension(width, height);
    }

    //摆放子View
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left;
        int top = getPaddingTop();
        int right;
        int bottom;
        int maxHeight = 0;
        for (List<View> childViews : mChildViews) {
            left = getPaddingLeft();
            for (View childView : childViews) {
                //获取子View的 margin
                MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
                left += params.leftMargin;
                int childTop = top + params.topMargin;
                right = left + childView.getMeasuredWidth();
                bottom = childTop + childView.getMeasuredHeight();
                Log.d("TAG", "left -> " + left + " top ->" + top + "right -> " + right + " bottom ->" + bottom);
                //摆放
                childView.layout(left, childTop, right, bottom);
                left += childView.getMeasuredWidth() + params.rightMargin;
                int childHeight = childView.getMeasuredHeight() + params.topMargin + params.bottomMargin;
                maxHeight = Math.max(childHeight, maxHeight);
            }
            top += maxHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
