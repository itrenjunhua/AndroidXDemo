package com.renj.androidx.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.renj.androidx.R
import com.renj.androidx.databinding.FragmentActivityBinding

class FragmentActivity : AppCompatActivity() {
    private lateinit var fragmentActivityBinding: FragmentActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentActivityBinding = DataBindingUtil.setContentView(this, R.layout.fragment_activity)

        fragmentActivityBinding.run {
            ivDrawerControl.setOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
        }
    }
}