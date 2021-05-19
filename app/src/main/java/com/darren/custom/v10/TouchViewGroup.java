package com.darren.custom.v10;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * date  5/19/21  3:31 PM
 * author  DarrenHang
 */
public class TouchViewGroup extends RelativeLayout {
    public TouchViewGroup(Context context) {
        super(context);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("V10Activity", "onTouchEvent -> TouchViewGroup" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("V10Activity", "dispatchTouchEvent -> TouchViewGroup" + event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
