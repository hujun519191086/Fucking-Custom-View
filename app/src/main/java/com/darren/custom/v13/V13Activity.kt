package com.darren.custom.v13

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.darren.custom.R
import com.darren.custom.databinding.ActivityV13Binding

/**
date  6/3/21  2:20 PM
author  DarrenHang
 */
class V13Activity : AppCompatActivity() {

    lateinit var v13Binding: ActivityV13Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v13Binding = DataBindingUtil.setContentView(this, R.layout.activity_v13)
        v13Binding.nineView.setCallBack(object : NineView.CallBack {
            override fun password(pwd: String) {
                Log.d("response", pwd)
            }
        })
    }

}