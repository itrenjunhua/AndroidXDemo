package com.renj.jetpack.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.renj.jetpack.R
import com.renj.jetpack.databinding.PagingListItemBinding

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-17   17:08
 *
 * 描述：
 *
 * 修订历史：
 *
 * ======================================================================
 */
class PagingListAdapter : PagingDataAdapter<UserBean, ViewHolder>(DataDiff) {

    object DataDiff : DiffUtil.ItemCallback<UserBean>() {
        /**
         * 比对新旧条目是否是同一个条目；
         */
        override fun areItemsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
            return oldItem == newItem
        }

        /**
         * 当上面的方法确定是同一个条目之后，这里比对条目的内容是否一样，不一样则会更新条目UI
         */
        override fun areContentsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
            return oldItem == newItem
        }

        /**
         * 这个方法对应 RcyclerView的 adapter的 第三个参数；用于条目内部的局部刷新；可选
         */
        override fun getChangePayload(oldItem: UserBean, newItem: UserBean): Any? {
            return super.getChangePayload(oldItem, newItem)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val pagingListItemBinding = DataBindingUtil.inflate<PagingListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.paging_list_item,
            parent,
            false
        )
        return ViewHolder(pagingListItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pagingListItemBinding = (holder as ViewHolder).pagingListItemBinding
        pagingListItemBinding.setVariable(BR.userBean, getItem(position))
        pagingListItemBinding.executePendingBindings()
    }

    class ViewHolder(var pagingListItemBinding: PagingListItemBinding) :
        RecyclerView.ViewHolder(pagingListItemBinding.root)
}