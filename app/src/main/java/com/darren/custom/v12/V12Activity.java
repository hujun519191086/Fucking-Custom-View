package com.darren.custom.v12;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.darren.custom.R;
import com.darren.custom.databinding.ActivityV12Binding;

import java.util.ArrayList;

/**
 * date  5/27/21  2:59 PM
 * author  DarrenHang
 */
public class V12Activity extends AppCompatActivity {

    ActivityV12Binding binding;
    ArrayList<String> list;
    CommonRecyAdapter<String> commonRecyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_v12);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "====");
        }
        commonRecyAdapter = new CommonRecyAdapter<String>(this, list, R.layout.item_v12) {
            @Override
            public void convert(ViewRecyHolder holder, String s, int position) {
                holder.setText(R.id.tv_v12, s);
            }
        };
        binding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.mRecyclerView.setAdapter(commonRecyAdapter);
    }

}
