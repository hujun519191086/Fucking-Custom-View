package com.darren.custom.v8;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV8Binding;

/**
 * date  12/16/20  10:15 PM
 * author  DarrenHang
 */
public class V8Activity extends V8BaseActivity {

    ActivityV8Binding v8Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    private void initBinding(){
        v8Binding = DataBindingUtil.setContentView(this, R.layout.activity_v8);
    }

}
