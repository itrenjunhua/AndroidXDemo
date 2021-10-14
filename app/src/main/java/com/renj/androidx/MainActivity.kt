package com.renj.androidx

import com.renj.androidx.base.BaseActivity
import com.renj.androidx.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getCurrentActivity(): BaseActivity<ActivityMainBinding, MainViewModel> {
        return this@MainActivity
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData(viewDataBinding: ActivityMainBinding?, viewModel: MainViewModel?) {
        viewDataBinding?.viewModel = viewModel
    }
}