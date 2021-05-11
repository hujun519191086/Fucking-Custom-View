package com.darren.custom.v10;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
        v10Binding.vwTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("V10Activity", "onTouch -> " + event.getAction());
                return false;
            }
        });

        v10Binding.vwTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("V10Activity", "onClick");
            }
        });
    }


}
