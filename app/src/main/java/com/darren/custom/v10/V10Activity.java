package com.darren.custom.v10;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV10Binding;

/**
 * date  3/29/21  9:50 AM
 * author  DarrenHang
 */
public class V10Activity extends AppCompatActivity {

    ActivityV10Binding v10Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v10Binding = DataBindingUtil.setContentView(this, R.layout.activity_v10);
    }
}
