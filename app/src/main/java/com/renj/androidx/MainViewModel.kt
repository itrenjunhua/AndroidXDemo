package com.renj.androidx

import android.app.Application
import android.content.Intent
import android.view.View
import com.renj.androidx.base.BaseViewModel
import com.renj.androidx.fvp.FVPActivity

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
class MainViewModel(application: Application, activity: MainActivity) :
    BaseViewModel(application, activity) {

    fun clickViewPager(view: View) {
        activity?.startActivity(Intent(activity, FVPActivity::class.java))
    }
}