package com.jkempster34.deathclockadvanced.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jkempster34.deathclockadvanced.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeathClockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deathclock)
    }
}