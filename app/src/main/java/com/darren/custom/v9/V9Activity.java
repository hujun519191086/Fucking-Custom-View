package com.darren.custom.v9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV9Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * date  2/2/21  10:06 PM
 * author  DarrenHang
 */
public class V9Activity extends AppCompatActivity {


    ActivityV9Binding v9Binding;
    List<String> mItems;
    TagAdapter tagAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v9Binding = DataBindingUtil.setContentView(this, R.layout.activity_v9);
        initData();
        dataChange();
    }

    private void initData() {
        mItems = new ArrayList<>();
        mItems.add("qwertyuiop");
        mItems.add("asdfghjkl");
        mItems.add("qwertyuiop");
        mItems.add("zxcvbnm");
        mItems.add("zxcvbnm");
        mItems.add("zxcvbnm");
        mItems.add("zxcvbnm");
        mItems.add("zxcvbnm");
        tagAdapter = new TagAdapter() {
            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public View getView(int position, ViewGroup viewGroup) {
                TextView view = (TextView) LayoutInflater.from(V9Activity.this).inflate(R.layout.tag_item, viewGroup, false);
                view.setText(mItems.get(position));
                return view;
            }
        };
        v9Binding.mTagLayout.setAdapter(tagAdapter);
    }

    private void dataChange() {
        v9Binding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItems.add("qwertyuiop");
                mItems.add("asdfghjkl");
                mItems.add("qwertyuiop");
                mItems.add("zxcvbnm");
                mItems.add("zxcvbnm");
                mItems.add("zxcvbnm");
                mItems.add("zxcvbnm");
                mItems.add("zxcvbnm");
                v9Binding.mTagLayout.setAdapter(tagAdapter);
            }
        });
    }

}
