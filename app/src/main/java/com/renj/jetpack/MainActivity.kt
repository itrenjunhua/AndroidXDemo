package com.renj.jetpack

import com.renj.jetpack.base.BaseActivity
import com.renj.jetpack.databinding.ActivityMainBinding

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