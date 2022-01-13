package com.renj.jetpack.navigation

import androidx.navigation.fragment.findNavController
import com.renj.jetpack.R
import com.renj.jetpack.base.BaseFragment
import com.renj.jetpack.databinding.NavigationMainFragmentBinding

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
class NavMainFragment : BaseFragment<NavigationMainFragmentBinding, Nothing>() {

    override fun getLayoutId(): Int {
        return R.layout.navigation_main_fragment
    }

    override fun initData(viewDataBinding: NavigationMainFragmentBinding?, viewModel: Nothing?) {
        viewDataBinding?.run {
            btOpenFirst.setOnClickListener {
                val navFirstFragmentArgs =
                    NavFirstFragmentArgs("第一个Fragment，可以继续跳转", "#30FF0000", true)

                findNavController().navigate(
                    R.id.action_first_fragment,
                    navFirstFragmentArgs.toBundle()
                )
            }


            btOpenSecond.setOnClickListener {
                val navSecondFragmentArgs =
                    NavSecondFragmentArgs(
                        "第二个Fragment，不可以继续跳转",
                        "#3000FF00",
                        false
                    )

                findNavController().navigate(
                    R.id.action_second_fragment,
                    navSecondFragmentArgs.toBundle()
                )
            }
        }
    }
}