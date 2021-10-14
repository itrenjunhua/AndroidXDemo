package com.renj.androidx.fvp

import android.graphics.Color
import android.os.Bundle
import com.renj.androidx.R
import com.renj.androidx.base.BaseFragment
import com.renj.androidx.base.LazyFragment
import com.renj.androidx.databinding.FragmentActivityFragmentBinding

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
class FragmentReplace : LazyFragment<FragmentActivityFragmentBinding, Nothing>() {

    companion object {
        fun newInstance(): FragmentReplace {
            val args = Bundle()
            val fragment = FragmentReplace()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getCurrentFragment(): BaseFragment<FragmentActivityFragmentBinding, Nothing> {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_activity_fragment
    }

    override fun initData(
        viewDataBinding: FragmentActivityFragmentBinding?,
        viewModel: Nothing?
    ) {
        viewDataBinding?.run {
            flFragmentContent.setBackgroundColor(Color.parseColor("#88FF8989"))
            value = "Fragment Replace"
        }
    }

    override fun initListener(
        viewDataBinding: FragmentActivityFragmentBinding?,
        viewModel: Nothing?
    ) {

    }

}