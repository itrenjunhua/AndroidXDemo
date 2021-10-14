package com.renj.androidx.fvp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.google.android.material.tabs.TabLayout
import com.renj.androidx.R
import com.renj.androidx.base.BaseFragment
import com.renj.androidx.base.LazyFragment
import com.renj.androidx.databinding.FragmentViewPager2Binding

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-10-14   11:00
 *
 * 描述：ViewPager2 加载 Fragment。
 *
 * 修订历史：
 *
 * ======================================================================
 */
class VP2Fragment : LazyFragment<FragmentViewPager2Binding, Nothing>() {
    companion object {
        fun newInstance(): VP2Fragment {
            val args = Bundle()
            val fragment = VP2Fragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getCurrentFragment(): BaseFragment<FragmentViewPager2Binding, Nothing> {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_view_pager2
    }

    override fun initData(viewDataBinding: FragmentViewPager2Binding?, viewModel: Nothing?) {
        viewDataBinding?.run {
            val titles = ArrayList<String>()
            val fragments = ArrayList<Fragment>()
            tabLayout.run {
                for (i in 1..3) {
                    titles.add("Fragment${i}")
                    addTab(newTab().setText("Fragment${i}"))
                    var color = Color.parseColor("#80FF0000")
                    if (i == 2) {
                        color = Color.parseColor("#8000FF00")
                    } else if (i == 3) {
                        color = Color.parseColor("#800000FF")
                    }
                    fragments.add(FragmentChild.newInstance("ViewPager2 Fragment${i}", color))
                }

                setTabTextColors(Color.BLACK, Color.BLUE)
                setSelectedTabIndicatorColor(Color.BLUE)
                setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_BOTTOM)
            }


            viewPager.run {
                orientation = ORIENTATION_HORIZONTAL // 设置滑动方向-水平
                // orientation = ORIENTATION_VERTICAL // 设置滑动方向-垂直
                activity?.let { adapter = ViewPager2FragmentAdapter(it, fragments) }
            }
        }
    }

    class ViewPager2FragmentAdapter(
        activity: FragmentActivity,
        private var fragments: List<Fragment>
    ) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

}