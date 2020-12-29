package com.darren.custom.v8;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

/**
 * date  12/29/20  10:52 AM
 * author  DarrenHang
 */
public class V8Test1 {

    private Context context;
    private V8Test2.AttachInfoTest attachInfoTest;
    private V8Test2 mTest;

    Handler handler = new Handler();

    public V8Test1(Context context) {
        this.context = context;
        attachInfoTest = new V8Test2.AttachInfoTest(context,handler);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("response","Test1 执行=====");
            performTraversals();
        }
    };


    void scheduleTraversals() {
        handler.post(runnable);
    }

    private void performTraversals() {
        final V8Test2 host = mTest;
        host.dispatchAttachedToWindow(attachInfoTest);
        Log.d("response","Test1 执行 Measure");
        Log.d("response","Test1 执行 Layout");
        Log.d("response","Test1 执行 draw");
    }

    public void setTest(V8Test2 mTest) {
        this.mTest = mTest;
    }
}
