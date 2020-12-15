package com.darren.custom.v5;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.darren.custom.R;

/**
 * date  12/11/20  10:08 AM
 * author  DarrenHang
 */

public class ShapeView extends View {
    //画笔
    private Paint mCirclePaint,mSquarePaint,mTrianglePaint;
    //默认图形
    private Shape mShape = Shape.CIRCLE;
    //颜色
    private int circleColor = Color.YELLOW;
    private int squareColor = Color.BLUE;
    private int triangleColor = Color.GREEN;

    public enum Shape {
        CIRCLE, SQUARE, TRIANGLE
    }

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initCirclePaint();
        initSquarePaint();
        initTrianglePaint();
    }

    private void initAttr(Context context, AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);
        //圆颜色
        circleColor = array.getColor(R.styleable.ShapeView_circleColor,circleColor);
        //正方形颜色
        squareColor = array.getColor(R.styleable.ShapeView_circleColor,squareColor);
        //三角形颜色
        triangleColor = array.getColor(R.styleable.ShapeView_circleColor,triangleColor);
        //回收
        array.recycle();
    }

    private void initCirclePaint(){
        mCirclePaint = new Paint();
        //抗锯齿
        mCirclePaint.setAntiAlias(true);
        //设置颜色
        mCirclePaint.setColor(circleColor);
    }

    private void initSquarePaint(){
        mSquarePaint = new Paint();
        //抗锯齿
        mSquarePaint.setAntiAlias(true);
        //设置颜色
        mSquarePaint.setColor(squareColor);
    }

    private void initTrianglePaint(){
        mTrianglePaint = new Paint();
        //抗锯齿
        mTrianglePaint.setAntiAlias(true);
        //设置颜色
        mTrianglePaint.setColor(triangleColor);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
        switch (mShape) {
            case CIRCLE:
                //画圆形
                int center = getWidth() / 2;
                canvas.drawCircle(center, center, center, mCirclePaint);
                break;
            case SQUARE:
                //画正方形
                canvas.drawRect(0, 0, getWidth(), getHeight(), mSquarePaint);
                break;
            case TRIANGLE:
                //画三角形
                Path path = new Path();
                path.moveTo(getWidth()/2, 0);
                path.lineTo(0, (float) (getWidth() / 2 * Math.sqrt(3)));
                path.lineTo(getWidth(), (float) (getWidth() / 2 * Math.sqrt(3)));
                path.close();
                canvas.drawPath(path, mTrianglePaint);
                break;
        }
    }

    public void exchange() {
        switch (mShape) {
            case CIRCLE:
                //正方形
                mShape = Shape.SQUARE;
                break;
            case SQUARE:
                //画三角形
                mShape = Shape.TRIANGLE;
                break;
            case TRIANGLE:
                //画圆形
                mShape = Shape.CIRCLE;
                break;
        }
        invalidate();
    }

}
