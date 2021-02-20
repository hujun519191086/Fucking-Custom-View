package com.darren.custom.v9;

import android.view.View;
import android.view.ViewGroup;

/**
 * date  2/20/21  3:13 PM
 * author  DarrenHang
 */
public abstract class TagAdapter {
    //获取条目
    public abstract int getCount();
    //获取View
    public abstract View getView(int position, ViewGroup viewGroup);
}
