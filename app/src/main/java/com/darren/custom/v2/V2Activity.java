package com.darren.custom.v2;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
        v2Binding.arcView.setMaxProgress(100);
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(0,80);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int)animation.getAnimatedValue();
                v2Binding.arcView.setCurrentProgress(progress);
            }
        });
        valueAnimator.start();
    }

}
