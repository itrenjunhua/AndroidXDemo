package com.renj.jetpack.paging

import android.content.Context
import android.content.Intent
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.renj.jetpack.R
import com.renj.jetpack.base.BaseActivity
import com.renj.jetpack.databinding.PagingListActivityBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-17   13:51
 *
 * 描述：
 *
 * 修订历史：
 *
 * ======================================================================
 */
class PagingListActivity : BaseActivity<PagingListActivityBinding, PagingListVM>() {
    private lateinit var pagingListAdapter: PagingListAdapter


    object Commend {
        fun start(context: Context) {
            context.startActivity(Intent(context, PagingListActivity::class.java))
        }
    }

    override fun getCurrentActivity(): BaseActivity<PagingListActivityBinding, PagingListVM> {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.paging_list_activity
    }

    override fun initData(viewDataBinding: PagingListActivityBinding?, viewModel: PagingListVM?) {
        // 初始化适配器和各种状态
        pagingListAdapter = PagingListAdapter()
        pagingListAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> viewDataBinding?.refreshView?.isRefreshing = false
                is LoadState.Loading -> viewDataBinding?.refreshView?.isRefreshing = true
                is LoadState.Error -> {
                    viewDataBinding?.refreshView?.isRefreshing = true
                    pagingListAdapter.retry() // 失败重试
                }
            }
        }

        // 控件相关设置
        viewDataBinding?.run {
            // 返回按钮点击监听
            pagingTitle.setOnBackViewClickListener { finish() }

            // RecycleView设置
            recyclerView.layoutManager =
                LinearLayoutManager(this@PagingListActivity, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = pagingListAdapter

            refreshView.setOnRefreshListener {
                pagingListAdapter.refresh() // 刷新
                // pagingListAdapter.retry() // 失败重试
            }
        }

        // 开始加载数据，使用协程
        lifecycleScope.launch {
            viewModel?.listData?.collect {
                pagingListAdapter.submitData(it)
            }
        }
    }

}