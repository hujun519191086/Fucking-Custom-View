package com.darren.custom.v4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.darren.custom.R;

/**
 * date  12/10/20  10:36 AM
 * author  DarrenHang
 */
public class ProgressView extends View {

    private int mInnerColor = Color.BLACK;
    private int mOutColor = Color.BLUE;
    private int mWidth = 10;

    private int mTextSize = 15;
    private int mTextColor = Color.BLACK;

    private Paint mInnerPaint;
    private Paint mOutPaint;
    private Paint mTextPaint;

    private int CurrentProgress = 0;
    private int MaxProgress = 100;


    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initInnerPaint();
        initOutPaint();
        initTextPaint();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        //内圆颜色
        mInnerColor = array.getColor(R.styleable.ProgressView_innerColor, mInnerColor);
        //外圆颜色
        mOutColor = array.getColor(R.styleable.ProgressView_outColor, mOutColor);
        //圆大小
        mWidth = (int) array.getDimension(R.styleable.ProgressView_roundWidth, mWidth);

        //文字大小
        mTextSize = array.getDimensionPixelSize(R.styleable.ProgressView_progressTextSize, mTextSize);
        //文字颜色
        mTextColor = array.getColor(R.styleable.ProgressView_progressTextColor, mTextColor);
        array.recycle();
    }

    private void initInnerPaint() {
        mInnerPaint = new Paint();
        //抗锯齿
        mInnerPaint.setAntiAlias(true);
        //防抖动
        mInnerPaint.setDither(true);
        //设置大小
        mInnerPaint.setStrokeWidth(mWidth);
        //设置颜色
        mInnerPaint.setColor(mInnerColor);
        //设置style
        mInnerPaint.setStyle(Paint.Style.STROKE);
    }

    private void initOutPaint() {
        mOutPaint = new Paint();
        //抗锯齿
        mOutPaint.setAntiAlias(true);
        //防抖动
        mOutPaint.setDither(true);
        //设置大小
        mOutPaint.setStrokeWidth(mWidth);
        //设置颜色
        mOutPaint.setColor(mOutColor);
        //设置style
        mOutPaint.setStyle(Paint.Style.STROKE);
    }

    private void initTextPaint() {
        mTextPaint = new Paint();
        //抗锯齿
        mTextPaint.setAntiAlias(true);
        //防抖动
        mTextPaint.setDither(true);
        //颜色
        mTextPaint.setColor(mTextColor);
        //文字大小
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画内圆
        int center = getWidth() / 2;
        canvas.drawCircle(center, center, center - mWidth / 2, mInnerPaint);
        //画外圆
        RectF rectF = new RectF(mWidth / 2, mWidth / 2, getWidth() - mWidth / 2, getHeight() - mWidth / 2);
        if (MaxProgress == 0) return;
        float angle = (float) CurrentProgress / MaxProgress;
        canvas.drawArc(rectF, 0, angle * 360, false, mOutPaint);
        //画文字
        String text = ((int) (angle * 100)) + "%";
        int textWidth = (int) mTextPaint.measureText(text);
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int y = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + y, mTextPaint);
    }

    public void setCurrentProgress(int currentProgress) {
        CurrentProgress = currentProgress;
        invalidate();
    }

    public void setMaxProgress(int maxProgress) {
        MaxProgress = maxProgress;
    }
}
