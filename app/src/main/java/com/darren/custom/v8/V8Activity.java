package com.darren.custom.v8;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV8Binding;

/**
 * date  12/16/20  10:15 PM
 * author  DarrenHang
 */
public class V8Activity extends V8BaseActivity {

    private String TAG = "V8Activity";
    ActivityV8Binding v8Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        //Log-1
        /*Log.d(TAG, "Button Height onCreate:" + v8Binding.btn.getMeasuredHeight());
        v8Binding.btn.post(new Runnable() {
            @Override
            public void run() {
                //Log-2
                Log.d(TAG, "Button Height post:" + v8Binding.btn.getMeasuredHeight());
            }
        });
        V8Test1 v8Test1 = new V8Test1(this);
        v8Test1.setTest(new V8Test2());
        v8Test1.scheduleTraversals();*/
    }

    private void initBinding() {
        v8Binding = DataBindingUtil.setContentView(this, R.layout.activity_v8);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log-3
        //Log.d(TAG, "Button Height onResume:" + v8Binding.btn.getMeasuredHeight());
    }
}
