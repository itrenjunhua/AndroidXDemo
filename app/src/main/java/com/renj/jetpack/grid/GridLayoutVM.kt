package com.renj.jetpack.grid

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.renj.jetpack.base.BaseViewModel
import com.renj.jetpack.expand.ktx.handlerString

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-18   17:40
 *
 * 描述：
 *
 * 修订历史：
 *
 * ======================================================================
 */
class GridLayoutVM(application: Application, activity: GridLayoutActivity) :
    BaseViewModel(application, activity) {

    var resultContent = MutableLiveData<String>()

    fun clickClear(view: View) {
        resultContent.postValue("")
    }

    fun clickBack(view: View) {
        if (resultContent.value.isNullOrEmpty()) {
            resultContent.postValue("")
        } else {
            resultContent.postValue(
                resultContent.value?.length?.minus(1)?.let {
                    resultContent.value?.substring(0, it)
                }
            )
        }
    }

    fun clickButton(view: View, number: String) {
        resultContent.postValue(resultContent.value.handlerString() + number)
    }


}