package com.darren.custom.v2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV2Binding;

/**
 * date  12/8/20  9:31 AM
 * author  DarrenHang
 */
public class V2Activity extends AppCompatActivity {

    ActivityV2Binding v2Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initView();
    }

    private void initBinding() {
        v2Binding = DataBindingUtil.setContentView(this, R.layout.activity_v2);
    }

    private void initView() {
        v2Binding.arcView
                .setCurrentProgress(80)
                .setMaxProgress(100);
    }

}
