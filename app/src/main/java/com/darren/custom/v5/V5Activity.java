package com.darren.custom.v5;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV5Binding;

/**
 * date  12/11/20  10:17 AM
 * author  DarrenHang
 */
public class V5Activity extends AppCompatActivity {

    ActivityV5Binding v5Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initView();
    }

    private void initBinding(){
        v5Binding = DataBindingUtil.setContentView(this, R.layout.activity_v5);
    }

    private void initView(){
        v5Binding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exchange();
            }
        });
    }

    private void exchange(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    v5Binding.shapeView.exchange();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
