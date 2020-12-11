package com.darren.custom.v1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.Nullable;
import com.darren.custom.R;


/**
 * date  2020/12/3  4:46 PM
 * author  DarrenHang
 */
public class CustomTextView extends View {
    //文本
    private String mText;
    //文字颜色
    private int mTextColor = Color.BLACK;
    //文字大小
    private int mTextSize;
    //画笔
    private Paint mPaint;

    //在初始化时调用
    public CustomTextView(Context context) {
        this(context, null);
    }

    //在 Layout 中调用
    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //在 Layout 中设置 style 调用
    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 第一步获取属性
         */
        initAttr(context, attrs);
        //初始化 Paint
        initPaint();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        //第一步获取 TypedArray
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        //第二步获取属性
        mText = array.getString(R.styleable.CustomTextView_cusText);
        mTextColor = array.getColor(R.styleable.CustomTextView_cusTextColor, mTextColor);
        //默认是 px,将 sp 转换成 px
        mTextSize = array.getDimensionPixelSize(R.styleable.CustomTextView_cusTextSize, sp2px(mTextSize));
        //回收
        array.recycle();
    }

    private void initPaint() {
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        //设置大小
        mPaint.setTextSize(mTextSize);
        //设置颜色
        mPaint.setColor(mTextColor);
    }


    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    /**
     * 第二步测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //第一步获取测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //第二步获取数值
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //第三步判断模式,如果是 wrap_content 就需要计算宽高
        //通过画笔 Paint 测量文本的宽高
        Rect rect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), rect);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = rect.width() + getPaddingLeft() + getPaddingRight();
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = rect.height() + getPaddingTop() + getPaddingBottom();
        }
        //第四部设置宽高
        setMeasuredDimension(widthSize, heightSize);
    }

    /**
     * 第三步绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getPaddingLeft();
        //需要计算基线 baseline 位置
        //获取
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        //(fontMetricsInt.bottom - fontMetricsInt.top)/2 - fontMetricsInt.bottom
        //(fontMetricsInt.top - fontMetricsInt.bottom) / 2 - fontMetricsInt.top
        int y = (fontMetricsInt.bottom - fontMetricsInt.top)/2 - fontMetricsInt.bottom;
        int baseline = getHeight() / 2 + y;
        canvas.drawText(mText, x, baseline, mPaint);
    }
}
