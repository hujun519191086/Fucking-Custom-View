package com.darren.custom.v4;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV4Binding;

/**
 * date  12/10/20  9:46 PM
 * author  DarrenHang
 */
public class V4Activity extends AppCompatActivity {

    ActivityV4Binding v4Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initView();
        initAnimation();
    }

    private void initBinding(){
        v4Binding = DataBindingUtil.setContentView(this, R.layout.activity_v4);
    }

    private void initView(){
        v4Binding.progressView.setMaxProgress(100);
    }

    private void initAnimation(){
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(0,100);
        valueAnimator.setDuration(4000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int)animation.getAnimatedValue();
                v4Binding.progressView.setCurrentProgress(progress);
            }
        });
        valueAnimator.start();
    }

}
