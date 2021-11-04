package com.renj.jetpack.fvp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.renj.jetpack.R
import com.renj.jetpack.base.BaseFragment
import com.renj.jetpack.base.LazyFragment
import com.renj.jetpack.databinding.FragmentViewPagerBinding

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-10-14   11:00
 *
 * 描述：ViewPager 加载 Fragment。
 *
 * 注意在AndroidX中的 FragmentStatePagerAdapter 类，增加了一个带 behavior 的构造。
 * 我们都知道，使用 ViewPager 嵌套 Fragment 时，Fragment的生命周期回调是非正常的。这个参数有两个取值：
 *
 * BEHAVIOR_SET_USER_VISIBLE_HINT：跟之前的方式是一致的，我们可以通过 setUserVisibleHint 结合 fragment 的生命周期来监听
 *
 * BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT：ViewPager 中切换 Fragment，setUserVisibleHint 方法将不再被调用，他会确保 onResume 的正确调用时机
 *
 * 修订历史：
 *
 * ======================================================================
 */
class VPFragment : LazyFragment<FragmentViewPagerBinding, Nothing>() {
    companion object {
        fun newInstance(): VPFragment {
            val args = Bundle()
            val fragment = VPFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getCurrentFragment(): BaseFragment<FragmentViewPagerBinding, Nothing> {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_view_pager
    }

    override fun initData(viewDataBinding: FragmentViewPagerBinding?, viewModel: Nothing?) {
        viewDataBinding?.run {
            val titles = ArrayList<String>()
            val fragments = ArrayList<Fragment>()
            tabLayout.run {
                for (i in 1..3) {
                    titles.add("Fragment${i}")
                    addTab(newTab().setText("Fragment${i}"))
                    var color = Color.parseColor("#80FF0000")
                    if (i == 1) {
                        color = Color.parseColor("#8000FF00")
                    } else if (i == 2) {
                        color = Color.parseColor("#800000FF")
                    }
                    fragments.add(FragmentChild.newInstance("ViewPager Fragment${i}", color))
                }

                setTabTextColors(Color.BLACK, Color.BLUE)
                setSelectedTabIndicatorColor(Color.BLUE)
                setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_BOTTOM)

                setupWithViewPager(viewPager)
            }

            // viewPager.adapter = ViewPagerFragmentAdapter(childFragmentManager, titles, fragments)
            viewPager.adapter = ViewPagerFragmentAdapter2(childFragmentManager, titles, fragments)
        }
    }

    /**
     * 使用带 behavior 的构造，在 AndroidX 中为建议使用
     */
    class ViewPagerFragmentAdapter2(
        fragmentManager: FragmentManager,
        private var titles: ArrayList<String>,
        private var fragments: ArrayList<Fragment>
    ) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

    }

    /**
     * 使用不带 behavior 的构造，在 AndroidX 中已被标记为过时
     */
    class ViewPagerFragmentAdapter(
        fragmentManager: FragmentManager,
        private var titles: ArrayList<String>,
        private var fragments: ArrayList<Fragment>
    ) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

    }
}