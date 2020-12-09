package com.darren.custom.v3;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV3Binding;

/**
 * date  12/9/20  10:26 AM
 * author  DarrenHang
 */
public class V3Activity extends AppCompatActivity implements View.OnClickListener {

    private ActivityV3Binding v3Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initView();
    }

    private void initBinding(){
        v3Binding = DataBindingUtil.setContentView(this,R.layout.activity_v3);
    }

    private void initView(){
        v3Binding.btnLeft.setOnClickListener(this);
        v3Binding.btnRight.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left:
                tarck(TrackView.Direction.LEFT_TO_RIGHT);
                break;
            case R.id.btn_right:
                tarck(TrackView.Direction.RIGHT_TO_LEFT);
                break;

        }
    }

    private void tarck(TrackView.Direction dic){
        v3Binding.trackView.setDirection(dic);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float)animation.getAnimatedValue();
                v3Binding.trackView.setCurrentProgress(progress);
            }
        });
        valueAnimator.start();
    }
}
