package com.darren.custom.v8;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

/**
 * date  12/29/20  10:52 AM
 * author  DarrenHang
 */
public class V8Test2 {

    AttachInfoTest attachInfoTest;

    final static class AttachInfoTest {

        final Handler handler;
        final Context context;

        AttachInfoTest(Context mContext, Handler mHandler) {
            context = mContext;
            handler = mHandler;
        }

    }

    void dispatchAttachedToWindow(AttachInfoTest info) {
        this.attachInfoTest = info;
        executeActions(attachInfoTest.handler);
    }

    public void executeActions(Handler handler) {
        synchronized (this) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("response","Test2执行=====");
                }
            }, 0);
        }
    }


}
