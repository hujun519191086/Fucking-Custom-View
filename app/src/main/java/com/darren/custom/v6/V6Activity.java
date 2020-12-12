package com.darren.custom.v6;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV6Binding;

/**
 * date  12/11/20  4:07 PM
 * author  DarrenHang
 */
public class V6Activity extends AppCompatActivity {

    private ActivityV6Binding v6Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    private void initBinding(){
        v6Binding = DataBindingUtil.setContentView(this, R.layout.activity_v6);
    }

}
