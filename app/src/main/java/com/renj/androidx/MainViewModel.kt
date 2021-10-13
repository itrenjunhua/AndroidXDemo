package com.renj.androidx

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.renj.androidx.fragment.FragmentActivity

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-10-13   14:03
 *
 * 描述：
 *
 * 修订历史：
 *
 * ======================================================================
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    fun clickViewPager(view: View) {
        view.context.startActivity(Intent(view.context, FragmentActivity::class.java))
    }
}