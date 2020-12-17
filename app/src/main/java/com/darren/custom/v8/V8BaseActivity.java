package com.darren.custom.v8;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.LayoutInflaterCompat;

/**
 * date  12/16/20  10:37 PM
 * author  DarrenHang
 */
public class V8BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        inflateTest();
        super.onCreate(savedInstanceState);
    }

    private void inflateTest() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater, new LayoutInflater.Factory2() {
            @Nullable
            @Override
            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                Log.d("View:", name);
                if (name.equals("Button")) {
                    TextView textView = new TextView(context);
                    textView.setText("test");
                    textView.setTextColor(Color.BLACK);
                    return textView;
                }
                return null;
            }

            @Nullable
            @Override
            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                return onCreateView(null, name, context, attrs);
            }
        });
    }

}
