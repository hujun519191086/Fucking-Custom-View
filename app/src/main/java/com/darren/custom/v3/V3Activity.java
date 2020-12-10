package com.darren.custom.v3;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV3Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * date  12/9/20  10:26 AM
 * author  DarrenHang
 */
public class V3Activity extends AppCompatActivity implements View.OnClickListener {

    private ActivityV3Binding v3Binding;
    private String[] items = {"直播", "推荐", "视频", "图片", "段子", "精华"};
    private List<TrackView> mIndicators;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initView();
        initIndicator();
        initViewPager();
    }

    private void initBinding(){
        v3Binding = DataBindingUtil.setContentView(this,R.layout.activity_v3);
    }

    private void initView(){
        v3Binding.btnLeft.setOnClickListener(this);
        v3Binding.btnRight.setOnClickListener(this);
        mIndicators = new ArrayList<>();
    }


    private void initIndicator() {
        for (int i = 0; i < items.length; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            TrackView trackView = new TrackView(this);
            trackView.setTextSize(30);
            trackView.setText(items[i]);
            trackView.setChangeColor(Color.RED);
            trackView.setLayoutParams(params);
            v3Binding.indication.addView(trackView);
            mIndicators.add(trackView);
        }
    }

    private void initViewPager() {
        v3Binding.mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(items[position]);
            }

            @Override
            public int getCount() {
                return items.length;
            }
        });
        v3Binding.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                TrackView left = mIndicators.get(position);
                left.setDirection(TrackView.Direction.RIGHT_TO_LEFT);
                left.setCurrentProgress(1 - positionOffset);

                if (position == items.length - 1) return;

                TrackView right = mIndicators.get(position + 1);
                right.setDirection(TrackView.Direction.LEFT_TO_RIGHT);
                right.setCurrentProgress(positionOffset);


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left:
                track(TrackView.Direction.LEFT_TO_RIGHT);
                break;
            case R.id.btn_right:
                track(TrackView.Direction.RIGHT_TO_LEFT);
                break;

        }
    }

    private void track(TrackView.Direction dic){
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
