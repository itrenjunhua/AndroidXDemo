package com.renj.jetpack.utils.image

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule


@GlideModule
class AppGlideModule : AppGlideModule() {
    override fun registerComponents(
        context: Context,
        glide: Glide,
        registry: Registry
    ) {

    }

    override fun applyOptions(
        context: Context,
        builder: GlideBuilder
    ) {
        builder.setMemoryCache(LruResourceCache(30 * 1024 * 1024))
        builder.setBitmapPool(LruBitmapPool(20 * 1024 * 1024))
    }
}