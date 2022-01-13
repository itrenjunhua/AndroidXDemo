package com.renj.jetpack.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.renj.jetpack.base.BaseActivity
import com.renj.jetpack.base.BaseFragment
import com.renj.jetpack.base.BaseViewModel

/**
 * ======================================================================
 *
 *
 * 作者：Renj
 *
 *
 * 创建时间：2021-11-05   11:22
 *
 *
 * 描述：ViewModel 创建帮助类
 *
 *
 * 修订历史：
 *
 *
 * ======================================================================
 */
object VMProviderUtils {

    @JvmStatic
    fun <F : BaseFragment<*, *>, VM : BaseViewModel> createViewModel(
        application: Application,
        fragment: F,
        clazz: Class<VM>
    ): VM {
        return ViewModelProvider(fragment, object : NewInstanceFactory() {
            override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
                return if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
                    try {
                        modelClass.getConstructor(
                            Application::class.java
                        )
                            .newInstance(application)
                    } catch (e: Exception) {
                        throw RuntimeException("Cannot create an instance of $modelClass", e)
                    }
                } else super.create(modelClass)
            }
        }).get(clazz)
    }

    @JvmStatic
    fun <A : BaseActivity<*, *>, VM : BaseViewModel> createViewModel(
        application: Application,
        activity: A,
        clazz: Class<VM>
    ): VM {
        return ViewModelProvider(activity, object : NewInstanceFactory() {
            override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
                return if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
                    try {
                        modelClass.getConstructor(
                            Application::class.java
                        )
                            .newInstance(application)
                    } catch (e: Exception) {
                        throw RuntimeException("Cannot create an instance of $modelClass", e)
                    }
                } else super.create(modelClass)
            }
        }).get(clazz)
    }
}