package com.renj.androidx.fm

import androidx.core.view.GravityCompat
import com.renj.androidx.R
import com.renj.androidx.base.BaseActivity
import com.renj.androidx.databinding.FragmentActivityBinding

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-10-13   16:03
 *
 * 描述：包含Fragment、Fragment懒加载、ViewPager、ViewPager2的实现
 *
 * 修订历史：
 *
 * ======================================================================
 */
class FragmentActivity : BaseActivity<FragmentActivityBinding, Nothing>() {

    override fun getCurrentActivity(): BaseActivity<FragmentActivityBinding, Nothing> {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_activity
    }

    override fun initData(viewDataBinding: FragmentActivityBinding?, viewModel: Nothing?) {
    }

    override fun initListener(viewDataBinding: FragmentActivityBinding?, viewModel: Nothing?) {
        viewDataBinding?.run {
            // 打开侧滑菜单
            ivDrawerControl.setOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
        }
    }
}