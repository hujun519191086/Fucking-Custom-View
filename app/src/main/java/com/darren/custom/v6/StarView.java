package com.darren.custom.v6;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.darren.custom.R;

/**
 * date  12/11/20  4:07 PM
 * author  DarrenHang
 */
public class StarView extends View {

    //默认数量
    private int mStarNumber = 5;
    private int mStarPadding = 5;

    //图片
    private Bitmap mNormalBitmap;
    private Bitmap mFocusBitmap;

    //选中数量
    private int CurrentNumber = 0;

    public StarView(Context context) {
        this(context, null);
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化属性
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StarView);
        //默认图片
        int mNormalId = array.getResourceId(R.styleable.StarView_normal, 0);
        if (mNormalId == 0) throw new RuntimeException("normal resource not set");
        mNormalBitmap = BitmapFactory.decodeResource(getResources(), mNormalId);

        //默认图片
        int mFocusId = array.getResourceId(R.styleable.StarView_focus, 0);
        if (mFocusId == 0) throw new RuntimeException("focus resource not set");
        mFocusBitmap = BitmapFactory.decodeResource(getResources(), mFocusId);

        //星星数量
        mStarNumber = array.getInt(R.styleable.StarView_starNumber, mStarNumber);
        //星星间隔
        mStarPadding = (int) array.getDimension(R.styleable.StarView_starPadding, mStarPadding);
        //回收
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //宽 星星总宽度 + 间隔 + padding
        int width = mNormalBitmap.getWidth() * mStarNumber + mStarPadding * (mStarNumber - 1) + getPaddingLeft() + getPaddingRight();
        //高 星星高度 + padding
        int height = mNormalBitmap.getHeight() + getPaddingTop() + getPaddingBottom();
        //设置宽高
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int starWidth = (getWidth() - getPaddingLeft() - getPaddingRight()) / mStarNumber;
        for (int i = 0; i < mStarNumber; i++) {
            if (CurrentNumber > i) {
                canvas.drawBitmap(mFocusBitmap, getPaddingLeft() + i * starWidth, getPaddingTop(), null);
            } else {
                canvas.drawBitmap(mNormalBitmap, getPaddingLeft() + i * starWidth, getPaddingTop(), null);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 减少多次绘制，根据实际情况处理事件
         * 这里我们只处理 MOVE 事件
         */
        switch (event.getAction()) {
            //case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //case MotionEvent.ACTION_UP:
                //获取在当前 View 中的位置
                float moveX = event.getX();
                //获取一个星星大小
                int starWidth = (getWidth() - getPaddingLeft() - getPaddingRight()) / mStarNumber;
                //获取当前位置
                int current = (int) moveX / starWidth + 1;
                if (current < 0) current = 0;
                if (current > mStarNumber) current = mStarNumber;
                if (this.CurrentNumber == current) return true;
                this.CurrentNumber = current;
                invalidate();
                break;
        }

        return true;
    }
}
