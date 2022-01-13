package com.renj.jetpack.paging

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.renj.jetpack.base.BaseViewModel

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-17   16:10
 *
 * 描述：
 *
 * 修订历史：
 *
 * ======================================================================
 */
class PagingListVM(application: Application) :
    BaseViewModel(application) {
    val listData = Pager(PagingConfig(pageSize = 20)) {
        PageDataSource()
    }.flow.cachedIn(viewModelScope)
}