package com.darren.custom.v3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.darren.custom.R;

/**
 * date  12/9/20  10:07 AM
 * author  DarrenHang
 */
public class TrackView extends TextView {

    private Paint mOriginPaint;
    private Paint mChangePaint;

    private float CurrentProgress = 0.0f;
    private Direction direction = Direction.LEFT_TO_RIGHT;


    public enum Direction {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public TrackView(Context context) {
        this(context, null);
    }

    public TrackView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TrackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TrackView);
        //默认颜色
        int originColor = array.getColor(R.styleable.TrackView_originColor, getTextColors().getDefaultColor());
        //改变颜色
        int changeColor = array.getColor(R.styleable.TrackView_changeColor, getTextColors().getDefaultColor());
        //回收
        array.recycle();
        mOriginPaint = getPaintByColor(originColor);
        mChangePaint = getPaintByColor(changeColor);
    }

    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        //设置颜色
        paint.setColor(color);
        //抗锯齿
        paint.setAntiAlias(true);
        //防抖动
        paint.setDither(true);
        //字体大小
        paint.setTextSize(getTextSize());
        return paint;
    }

    /**
     * 因为重写，所以一定要注释掉 super 方法
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        int middle = (int) (CurrentProgress * getWidth());
        if (direction == Direction.LEFT_TO_RIGHT) {
            drawText(canvas, mChangePaint, 0, middle);
            drawText(canvas, mOriginPaint, middle, getWidth());
        } else {
            drawText(canvas, mChangePaint, getWidth() - middle, getWidth());
            drawText(canvas, mOriginPaint, 0, getWidth() - middle);
        }
    }

    private void drawText(Canvas canvas, Paint paint, int start, int end) {
        canvas.save();
        //获取文字
        String text = getText().toString();
        Rect rect = new Rect(start, 0, end, getHeight());
        canvas.clipRect(rect);
        paint.getTextBounds(text, 0, text.length(), rect);
        int x = getWidth() / 2 - rect.width() / 2;
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int y = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseline = getHeight() / 2 + y;
        canvas.drawText(text, x, baseline, paint);
        canvas.restore();
    }

    public void setDirection(Direction dic) {
        this.direction = dic;
    }

    public void setCurrentProgress(float progress) {
        this.CurrentProgress = progress;
        invalidate();
    }

    public void setChangeColor(int red) {
        this.mChangePaint.setColor(red);
    }


}
