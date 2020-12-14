package com.darren.custom.v3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.darren.custom.R;

/**
 * date  2020/12/3  12:11 PM
 * author  DarrenHang
 */
public class ItemFragment extends Fragment {
    public static ItemFragment newInstance(String item){
        ItemFragment itemFragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",item);
        itemFragment.setArguments(bundle);
        return  itemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item,null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        Bundle bundle = getArguments();
        textView.setText(bundle.getString("title"));
        return view;
    }
}