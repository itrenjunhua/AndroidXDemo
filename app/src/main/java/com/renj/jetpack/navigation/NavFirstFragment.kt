package com.renj.jetpack.navigation

import android.graphics.Color
import androidx.navigation.fragment.findNavController
import com.renj.jetpack.R
import com.renj.jetpack.base.BaseFragment
import com.renj.jetpack.databinding.NavigationFirstFragmentBinding

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-05   15:35
 *
 * 描述：
 *
 * 修订历史：
 *
 * ======================================================================
 */
class NavFirstFragment : BaseFragment<NavigationFirstFragmentBinding, Nothing>() {

    override fun getLayoutId(): Int {
        return R.layout.navigation_first_fragment
    }

    override fun initData(viewDataBinding: NavigationFirstFragmentBinding?, viewModel: Nothing?) {
        viewDataBinding?.run {
            tvContent.text = arguments?.getString("content")
            flContent.setBackgroundColor(Color.parseColor(arguments?.getString("bgColor")))
            if (arguments?.getBoolean("canJump") == true) {

                tvJump.text = "跳转"
                tvJump.setOnClickListener {

                    val navSecondFragmentArgs =
                        NavSecondFragmentArgs(
                            "我是由第一个Fragment打开的新页面，还可以继续跳转",
                            "#300000FF",
                            true
                        )

                    findNavController().navigate(
                        R.id.action_second_fragment2,
                        navSecondFragmentArgs.toBundle()
                    )
                }
            } else {
                tvJump.text = "返回"
                tvJump.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }
    }
}