package com.jkempster34.myfive.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jkempster34.myfive.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyFive)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myfive)
    }
}