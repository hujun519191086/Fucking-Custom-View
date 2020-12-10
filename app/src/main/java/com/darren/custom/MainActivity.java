package com.darren.custom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.darren.custom.databinding.ActivityMainBinding;
import com.darren.custom.v1.V1Activity;
import com.darren.custom.v2.V2Activity;
import com.darren.custom.v3.V3Activity;

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
    }

    private void initClick() {
        mainBinding.btnCustomView.setOnClickListener(this);
        mainBinding.btnArcView.setOnClickListener(this);
        mainBinding.btnTrackView.setOnClickListener(this);
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
        }
    }
}