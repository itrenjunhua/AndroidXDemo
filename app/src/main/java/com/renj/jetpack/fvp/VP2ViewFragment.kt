package com.renj.jetpack.fvp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.renj.jetpack.R
import com.renj.jetpack.base.BaseFragment
import com.renj.jetpack.base.LazyFragment
import com.renj.jetpack.databinding.FragmentViewPager2Binding

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-10-14   11:00
 *
 * 描述：ViewPager2 加载普通View
 *
 * 修订历史：
 *
 * ======================================================================
 */
class VP2ViewFragment : LazyFragment<FragmentViewPager2Binding, Nothing>() {
    companion object {
        fun newInstance(): VP2ViewFragment {
            val args = Bundle()
            val fragment = VP2ViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_view_pager2
    }

    override fun initData(viewDataBinding: FragmentViewPager2Binding?, viewModel: Nothing?) {
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
                // 增加监听
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        viewPager.currentItem = tab!!.position
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }
                })
            }

            viewPager.run {
                val images = ArrayList<Int>()
                images.add(R.mipmap.ic_png1)
                images.add(R.mipmap.ic_png2)
                images.add(R.mipmap.ic_png3)

                orientation = ViewPager2.ORIENTATION_HORIZONTAL // 设置滑动方向-水平
                // orientation = ViewPager2.ORIENTATION_VERTICAL // 设置滑动方向-垂直
                adapter = ViewPager2ViewAdapter(images)

                // 增加监听
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        tabLayout.getTabAt(position)?.select()
                    }
                })
            }
        }
    }

    class ViewPager2ViewAdapter(
        private var images: ArrayList<Int>
    ) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val imageView = ImageView(parent.context)
            imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            return ViewHolder(imageView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            (holder.itemView as ImageView).setImageResource(images[position])
        }

        override fun getItemCount(): Int {
            return images.size
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}