package com.bayut.caching.component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bayut.caching.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }
}