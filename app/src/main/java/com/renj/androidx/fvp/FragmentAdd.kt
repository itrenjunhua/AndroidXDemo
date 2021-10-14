package com.renj.androidx.fvp

import android.graphics.Color
import android.os.Bundle
import com.renj.androidx.R
import com.renj.androidx.base.BaseFragment
import com.renj.androidx.base.LazyFragment
import com.renj.androidx.databinding.FragmentActivityAddBinding

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-10-14   9:21
 *
 * 描述：使用 replace 的Fragment
 *
 * 修订历史：
 *
 * ======================================================================
 */
class FragmentAdd : LazyFragment<FragmentActivityAddBinding, Nothing>() {
    private lateinit var child1: FragmentChild
    private lateinit var child2: FragmentChild

    companion object {
        fun newInstance(): FragmentAdd {
            val args = Bundle()
            val fragment = FragmentAdd()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getCurrentFragment(): BaseFragment<FragmentActivityAddBinding, Nothing> {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_activity_add
    }

    override fun initData(
        viewDataBinding: FragmentActivityAddBinding?,
        viewModel: Nothing?
    ) {
        child1 = FragmentChild.newInstance("Fragment1", Color.YELLOW)
        child2 = FragmentChild.newInstance("Fragment2", Color.GREEN)

        viewDataBinding?.run {
            childFragmentManager.beginTransaction()
                .add(R.id.fl_content, child1)
                .add(R.id.fl_content, child2)
                .hide(child2)
                .show(child1)
                .commitAllowingStateLoss()
        }
    }

    override fun initListener(
        viewDataBinding: FragmentActivityAddBinding?,
        viewModel: Nothing?
    ) {
        viewDataBinding?.run {
            btFragment1.setOnClickListener {
                childFragmentManager.beginTransaction()
                    .hide(child2)
                    .show(child1)
                    .commitAllowingStateLoss()
            }

            btFragment2.setOnClickListener {
                childFragmentManager.beginTransaction()
                    .hide(child1)
                    .show(child2)
                    .commitAllowingStateLoss()
            }
        }
    }

}