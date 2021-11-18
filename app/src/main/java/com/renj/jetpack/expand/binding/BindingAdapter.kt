package com.renj.jetpack.expand.binding

import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.databinding.BindingAdapter
import com.renj.jetpack.expand.ktx.loadImage
import com.renj.jetpack.utils.UIUtils
import java.io.File

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-18   9:00
 *
 * 描述：图片加载 BindingAdapter
 *
 * 修订历史：
 *
 * ======================================================================
 */
object BindingAdapter {
    @BindingAdapter(
        "url", "uri", "resId", "file", "bitmap", "thumbnail",
        "circle", "circleBorderWidth", "circleBorderColor",
        "corners", "skipCache", "defResId", "errResId",
        requireAll = false
    )
    @JvmStatic
    fun loadImage(
        imageView: ImageView,
        url: String? = null,
        uri: Uri? = null,
        @RawRes @DrawableRes
        resId: Int = -1,
        file: File? = null,
        bitmap: Bitmap? = null,
        thumbnail: String? = null,
        circle: Boolean = false,
        circleBorderWidth: Int = 0,
        @ColorInt
        circleBorderColor: Int = Color.WHITE,
        corners: Int = 0,
        skipCache: Boolean = false,
        @DrawableRes defResId: Int = -1,
        @DrawableRes errResId: Int = defResId
    ) {
        imageView.loadImage(
            url,
            uri,
            resId,
            file,
            bitmap,
            thumbnail,
            circle,
            UIUtils.dip2px(circleBorderWidth.toFloat()),
            circleBorderColor,
            UIUtils.dip2px(corners.toFloat()),
            skipCache,
            defResId,
            errResId
        )
    }
}