package com.darren.custom.v2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.darren.custom.R;

/**
 * date  12/8/20  9:27 AM
 * author  DarrenHang
 */
public class ArcView extends View {

    //外环默认颜色
    private int mInnerColor = Color.BLACK;
    //内环默认颜色
    private int mOutColor = Color.BLUE;
    //环默认大小
    private int mWidth = 10;

    //文字
    private String mText;
    //文字默认大小
    private int mTextSize = 10;
    //文字默认颜色
    private int mTextColor = Color.BLACK;

    private Paint mInnerPaint;
    private Paint mOutPaint;
    private Paint mTextPaint;

    private int MaxProgress;
    private int CurrentProgress;

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initInnerPaint();
        initOutPaint();
        initTextPaint();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ArcView);
        //外环颜色
        mInnerColor = array.getColor(R.styleable.ArcView_arcInnerColor, mInnerColor);
        //内环颜色
        mOutColor = array.getColor(R.styleable.ArcView_arcOutColor, mOutColor);
        //环大小
        mWidth = (int) array.getDimension(R.styleable.ArcView_arcWidth, mWidth);
        //文字
        mText = array.getString(R.styleable.ArcView_arcText);
        //文字大小
        mTextSize = array.getDimensionPixelSize(R.styleable.ArcView_arcTextSize, mTextSize);
        //文字颜色
        mTextColor = array.getColor(R.styleable.ArcView_arcTextColor, mTextColor);
        array.recycle();
    }

    private void initInnerPaint() {
        mInnerPaint = new Paint();
        //抗锯齿
        mInnerPaint.setAntiAlias(true);
        //防抖动
        mInnerPaint.setDither(true);
        //宽度
        mInnerPaint.setStrokeWidth(mWidth);
        //颜色
        mInnerPaint.setColor(mInnerColor);
        //设置状态
        mInnerPaint.setStyle(Paint.Style.STROKE);
        //头部小圆点
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void initOutPaint() {
        mOutPaint = new Paint();
        //抗锯齿
        mOutPaint.setAntiAlias(true);
        //防抖动
        mOutPaint.setDither(true);
        //宽度
        mOutPaint.setStrokeWidth(mWidth);
        //颜色
        mOutPaint.setColor(mOutColor);
        //设置状态
        mOutPaint.setStyle(Paint.Style.STROKE);
        //头部小圆点
        mOutPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void initTextPaint() {
        mTextPaint = new Paint();
        //抗锯齿
        mTextPaint.setAntiAlias(true);
        //文字大小
        mTextPaint.setTextSize(mTextSize);
        //文字颜色
        mTextPaint.setColor(mTextColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //确保正方形，将短边的大小设为狂傲
        //宽
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //高
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //设置宽高
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画内环
        RectF rectF = new RectF(mWidth / 2, mWidth / 2, getWidth() - mWidth / 2, getHeight() - mWidth / 2);
        canvas.drawArc(rectF, 135, 270, false, mInnerPaint);
        //画内环
        if (MaxProgress == 0) return;
        float sweepAngle = (float) CurrentProgress / MaxProgress;
        canvas.drawArc(rectF, 135, sweepAngle * 270, false, mOutPaint);
        //画文字
        //测量文字宽度
        Rect rect = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), rect);
        int x = getWidth() / 2 - rect.width() / 2;
        //基线
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int y = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseline = getHeight() / 2 + y;
        canvas.drawText(mText, x, baseline, mTextPaint);
    }

    //设置最大进度
    public ArcView setMaxProgress(int max) {
        this.MaxProgress = max;
        return this;
    }

    //设置当前进度
    public ArcView setCurrentProgress(int current) {
        this.CurrentProgress = current;
        //重新绘制
        invalidate();
        return this;
    }

}
