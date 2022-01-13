package com.renj.jetpack.fvp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
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
 * 描述：ViewPager 加载普通View
 *
 * 修订历史：
 *
 * ======================================================================
 */
class VPViewFragment : LazyFragment<FragmentViewPagerBinding, Nothing>() {
    companion object {
        fun newInstance(): VPViewFragment {
            val args = Bundle()
            val fragment = VPViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_view_pager
    }

    override fun initData(viewDataBinding: FragmentViewPagerBinding?, viewModel: Nothing?) {
        viewDataBinding?.run {
            val titles = ArrayList<String>()
            tabLayout.run {
                for (i in 1..3) {
                    addTab(newTab().setText("View${i}"))
                    titles.add("View${i}")
                }

                setTabTextColors(Color.BLACK, Color.BLUE)
                setSelectedTabIndicatorColor(Color.BLUE)
                setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_BOTTOM)

                setupWithViewPager(viewPager)
            }

            val images = ArrayList<Int>()
            images.add(R.mipmap.ic_png1)
            images.add(R.mipmap.ic_png2)
            images.add(R.mipmap.ic_png3)
            viewPager.adapter = ViewPagerViewAdapter(titles, images)
        }
    }

    class ViewPagerViewAdapter(
        private var titles: ArrayList<String>,
        private var images: ArrayList<Int>
    ) : PagerAdapter() {
        override fun getCount(): Int {
            return images.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = ImageView(container.context)
            imageView.setImageResource(images[position])
            container.addView(imageView)
            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }
    }
}