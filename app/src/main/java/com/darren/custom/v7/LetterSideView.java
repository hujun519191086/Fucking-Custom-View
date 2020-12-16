package com.darren.custom.v7;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.darren.custom.R;

/**
 * date  12/14/20  9:29 AM
 * author  DarrenHang
 */
public class LetterSideView extends View {

    //颜色
    private int mNormalColor = Color.BLACK;
    private int mFocusColor = Color.BLUE;
    //文字大小
    private int mTextSize = 8;
    //字母
    private String[] letter = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    //画笔
    private Paint mNormalPaint, mFocusPaint;
    //记录当前字母
    private String currentLetter;
    //回调
    private LetterCallBack letterCallBack;

    public LetterSideView(Context context) {
        this(context, null);
    }

    public LetterSideView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setLetterCallBack(LetterCallBack letterCallBack) {
        this.letterCallBack = letterCallBack;
    }

    public LetterSideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化属性
        initAttr(context, attrs);
        initNormalPaint();
        initFocusPaint();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LetterSideView);
        //默认颜色
        mNormalColor = array.getColor(R.styleable.LetterSideView_normalColor, mNormalColor);
        //选中颜色
        mFocusColor = array.getColor(R.styleable.LetterSideView_focusColor, mFocusColor);
        //文字大小
        mTextSize = array.getDimensionPixelSize(R.styleable.LetterSideView_letterSize, sp2px(mTextSize));
        //回收
        array.recycle();
    }

    private void initNormalPaint() {
        mNormalPaint = new Paint();
        //抗锯齿
        mNormalPaint.setAntiAlias(true);
        //防抖动
        mNormalPaint.setDither(true);
        //颜色
        mNormalPaint.setColor(mNormalColor);
        //设置大小
        mNormalPaint.setTextSize(mTextSize);
    }

    private void initFocusPaint() {
        mFocusPaint = new Paint();
        //抗锯齿
        mFocusPaint.setAntiAlias(true);
        //防抖动
        mFocusPaint.setDither(true);
        //颜色
        mFocusPaint.setColor(mFocusColor);
        //设置大小
        mFocusPaint.setTextSize(mTextSize);
    }

    private int getTextWidth(String letter) {
        return (int) mNormalPaint.measureText(letter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //字母宽度
        int letterWidth = getTextWidth("W");
        //宽
        int width = getPaddingLeft() + getPaddingRight() + letterWidth;
        //高
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //设置宽高
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //每个字母的高度
        int item = (getHeight() - getPaddingTop() - getPaddingBottom()) / letter.length;
        for (int i = 0; i < letter.length; i++) {
            //每个字母的中心位置
            int letterYCenter = item / 2 + i * item + getPaddingTop();
            Paint.FontMetricsInt fontMetricsInt = mNormalPaint.getFontMetricsInt();
            int x = getWidth() / 2 - getTextWidth(letter[i]) / 2;
            int y = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            int baseLine = letterYCenter + y;
            if (letter[i].equals(currentLetter)) {
                canvas.drawText(letter[i], x, baseLine, mFocusPaint);
            } else {
                canvas.drawText(letter[i], x, baseLine, mNormalPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                letterCallBack.letterCallBack(currentLetter, false);
                break;
            case MotionEvent.ACTION_MOVE:
                //获取当前 Y 位置
                float currentY = event.getY();
                int item = (getHeight() - getPaddingTop() - getPaddingBottom()) / letter.length;
                int currentPosition = (int) currentY / item;
                if (currentPosition < 0) {
                    currentPosition = 0;
                }

                if (currentPosition > letter.length - 1) {
                    currentPosition = letter.length - 1;
                }

                if (letter[currentPosition].equals(currentLetter)) {
                    return true;
                }

                currentLetter = letter[currentPosition];

                if (letterCallBack != null) {
                    letterCallBack.letterCallBack(currentLetter, true);
                }

                invalidate();
                break;
        }
        return true;
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public interface LetterCallBack {
        void letterCallBack(String letter, boolean letterState);
    }
}
