package com.renj.jetpack.grid

import android.content.Context
import android.content.Intent
import com.renj.jetpack.R
import com.renj.jetpack.base.BaseActivity
import com.renj.jetpack.databinding.GridLayoutActivityBinding

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-18   17:38
 *
 * 描述：GridLayout 使用
 *
 * 修订历史：
 *
 * ======================================================================
 */
class GridLayoutActivity : BaseActivity<GridLayoutActivityBinding, GridLayoutVM>() {
    object Commend {
        fun start(context: Context) {
            context.startActivity(Intent(context, GridLayoutActivity::class.java))
        }
    }

    override fun getCurrentActivity(): BaseActivity<GridLayoutActivityBinding, GridLayoutVM> {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.grid_layout_activity
    }

    override fun initData(viewDataBinding: GridLayoutActivityBinding?, viewModel: GridLayoutVM?) {
        viewDataBinding?.gridLayoutVm = viewModel

        viewDataBinding?.gridTitle?.setOnBackViewClickListener { finish() }
    }
}