package com.darren.custom.v9;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV9Binding;

/**
 * date  2/2/21  10:06 PM
 * author  DarrenHang
 */
public class V9Activity extends AppCompatActivity {


    ActivityV9Binding v9Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v9Binding = DataBindingUtil.setContentView(this, R.layout.activity_v9);
    }
}
