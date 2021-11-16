package com.renj.jetpack.navigation

import android.graphics.Color
import androidx.navigation.fragment.findNavController
import com.renj.jetpack.R
import com.renj.jetpack.base.BaseFragment
import com.renj.jetpack.databinding.NavigationSecondFragmentBinding

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
class NavSecondFragment : BaseFragment<NavigationSecondFragmentBinding, Nothing>() {
    override fun getCurrentFragment(): BaseFragment<NavigationSecondFragmentBinding, Nothing> {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.navigation_second_fragment
    }

    override fun initData(viewDataBinding: NavigationSecondFragmentBinding?, viewModel: Nothing?) {
        viewDataBinding?.run {
            tvContent.text = arguments?.getString("content")
            flContent.setBackgroundColor(Color.parseColor(arguments?.getString("bgColor")))
            if (arguments?.getBoolean("canJump") == true) {
                tvJump.text = "跳转"
                tvJump.setOnClickListener {
                    val navFirstFragmentArgs =
                        NavFirstFragmentArgs(
                            "我是最后一个页面了，不可以继续跳转",
                            "#30FF00FF",
                            false
                        )

                    findNavController().navigate(
                        R.id.action_first_fragment2,
                        navFirstFragmentArgs.toBundle()
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