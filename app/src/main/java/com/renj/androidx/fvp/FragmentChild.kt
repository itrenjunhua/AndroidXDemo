package com.renj.androidx.fvp

import android.os.Bundle
import androidx.annotation.ColorInt
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
 * 描述：各个功能下的子Fragment，共用
 *
 * 修订历史：
 *
 * ======================================================================
 */
class FragmentChild : LazyFragment<FragmentActivityFragmentBinding, Nothing>() {

    companion object {
        fun newInstance(content: String, @ColorInt color: Int): FragmentChild {
            val args = Bundle()
            args.putString("value", content)
            args.putInt("color", color)
            val fragment = FragmentChild()
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
            arguments?.run {
                flFragmentContent.setBackgroundColor(getInt("color"))
                value = getString("value")
            }
        }
    }

    override fun initListener(
        viewDataBinding: FragmentActivityFragmentBinding?,
        viewModel: Nothing?
    ) {

    }

}