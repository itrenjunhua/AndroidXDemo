package com.renj.androidx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.renj.androidx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        activityMainBinding.viewModel =
            ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
    }
}