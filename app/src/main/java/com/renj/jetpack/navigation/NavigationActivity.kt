package com.renj.jetpack.navigation

import android.content.Context
import android.content.Intent
import androidx.navigation.Navigation
import com.renj.jetpack.R
import com.renj.jetpack.base.BaseActivity
import com.renj.jetpack.databinding.NavigationActivityBinding

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-05   11:03
 *
 * 描述：包含 Navigation 组件使用
 *
 * 修订历史：
 *
 * ======================================================================
 */
class NavigationActivity : BaseActivity<NavigationActivityBinding, Nothing>() {

    object Commend {
        fun start(context: Context) {
            context.startActivity(Intent(context, NavigationActivity::class.java))
        }
    }

    override fun getCurrentActivity(): BaseActivity<NavigationActivityBinding, Nothing> {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.navigation_activity
    }

    override fun initData(viewDataBinding: NavigationActivityBinding?, viewModel: Nothing?) {
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.fv_nav_host).navigateUp()
    }
}