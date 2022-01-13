package com.renj.jetpack

import com.renj.jetpack.base.BaseActivity
import com.renj.jetpack.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData(viewDataBinding: ActivityMainBinding?, mainVM: MainVM?) {
        viewDataBinding?.viewModel = mainVM
    }
}