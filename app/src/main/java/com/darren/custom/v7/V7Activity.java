package com.darren.custom.v7;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV7Binding;

/**
 * date  12/14/20  9:50 AM
 * author  DarrenHang
 */
public class V7Activity extends AppCompatActivity {

    ActivityV7Binding v7Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initView();
    }

    private void initBinding(){
        v7Binding = DataBindingUtil.setContentView(this, R.layout.activity_v7);
    }

    private void initView(){
        v7Binding.letterSideView.setLetterCallBack(new LetterSideView.LetterCallBack() {
            @Override
            public void letterCallBack(String letter, boolean letterState) {
                if(letterState){
                    v7Binding.tv.setVisibility(View.VISIBLE);
                    v7Binding.tv.setText(letter);
                }else {
                    v7Binding.tv.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}
