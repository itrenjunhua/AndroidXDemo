package com.renj.jetpack

import android.app.Application
import android.view.View
import com.renj.jetpack.base.BaseViewModel
import com.renj.jetpack.fvp.FVPActivity
import com.renj.jetpack.navigation.NavigationActivity

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
class MainVM(application: Application, activity: MainActivity) :
    BaseViewModel(application, activity) {

    fun clickViewPager(view: View) {
        FVPActivity.Commend.start(activity!!)
    }

    fun clickNavigation(view: View) {
        NavigationActivity.Commend.start(activity!!)
    }
}