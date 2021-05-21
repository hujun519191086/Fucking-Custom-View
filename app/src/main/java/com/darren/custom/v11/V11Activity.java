package com.darren.custom.v11;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV11Binding;

/**
 * date  5/20/21  3:39 PM
 * author  DarrenHang
 */
public class V11Activity extends AppCompatActivity {

    ActivityV11Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_v11);
    }
}
