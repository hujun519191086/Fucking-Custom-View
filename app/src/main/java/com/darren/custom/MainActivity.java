package com.darren.custom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.darren.custom.databinding.ActivityMainBinding;
import com.darren.custom.v1.V1Activity;
import com.darren.custom.v2.V2Activity;
import com.darren.custom.v3.V3Activity;
import com.darren.custom.v4.V4Activity;
import com.darren.custom.v5.V5Activity;
import com.darren.custom.v6.V6Activity;
import com.darren.custom.v7.V7Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initClick();
    }

    private void initBinding() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LayoutInflater.from(this).inflate(R.layout.activity_main,null,false);
    }

    private void initClick() {
        mainBinding.btnCustomView.setOnClickListener(this);
        mainBinding.btnArcView.setOnClickListener(this);
        mainBinding.btnTrackView.setOnClickListener(this);
        mainBinding.btnProgressView.setOnClickListener(this);
        mainBinding.shapeView.setOnClickListener(this);
        mainBinding.starView.setOnClickListener(this);
        mainBinding.letterSideView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_custom_view:
                intent = new Intent(this, V1Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_arc_view:
                intent = new Intent(this, V2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_track_view:
                intent = new Intent(this, V3Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_progress_view:
                intent = new Intent(this, V4Activity.class);
                startActivity(intent);
                break;
            case R.id.shape_view:
                intent = new Intent(this, V5Activity.class);
                startActivity(intent);
                break;
            case R.id.star_view:
                intent = new Intent(this, V6Activity.class);
                startActivity(intent);
                break;
            case R.id.letter_side_view:
                intent = new Intent(this, V7Activity.class);
                startActivity(intent);
                break;
        }
    }
}