package com.renj.androidx.fvp

import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
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
class FVPActivity : BaseActivity<FragmentActivityBinding, Nothing>() {

    override fun getCurrentActivity(): BaseActivity<FragmentActivityBinding, Nothing> {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_activity
    }

    override fun initData(viewDataBinding: FragmentActivityBinding?, viewModel: Nothing?) {
        menuOperator(FragmentReplace.newInstance(), "Fragment Replace")
    }

    override fun initListener(viewDataBinding: FragmentActivityBinding?, viewModel: Nothing?) {
        viewDataBinding?.run {
            // 打开侧滑菜单
            ivDrawerControl.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }

            // Fragment Replace
            tvFragmentReplace.setOnClickListener {
                menuOperator(FragmentReplace.newInstance(), "Fragment Replace")
            }
            // Fragment Add
            tvFragmentAdd.setOnClickListener {
                menuOperator(FragmentAdd.newInstance(), "Fragment Add")
            }
            // ViewPager View
            tvViewpagerView.setOnClickListener {
                menuOperator(VPViewFragment.newInstance(), "ViewPager View")
            }
            // ViewPager Fragment
            tvViewpagerFragment.setOnClickListener {
                menuOperator(VPFragment.newInstance(), "ViewPager Fragment")
            }
            // ViewPager2 View
            tvViewpager2View.setOnClickListener {
                menuOperator(VP2ViewFragment.newInstance(), "ViewPager2 View")
            }
            // ViewPager2 Fragment
            tvViewpager2Fragment.setOnClickListener {
                menuOperator(VP2Fragment.newInstance(), "ViewPager2 Fragment")
            }
        }
    }


    // ==================== 菜单操作 ========================= //
    private fun menuOperator(fragment: Fragment, title: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_content, fragment)
            .commit()

        viewDataBinding?.run {
            titleView.text = title
            drawerLayout.close()
        }
    }
}