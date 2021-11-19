package com.renj.jetpack.expand.ktx

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-19   11:22
 *
 * 描述：数字扩展
 *
 * 修订历史：
 *
 * ======================================================================
 */
fun Double.isZero(): Boolean {
    return this == 0.0
}

fun Double.notZero(): Boolean {
    return !isZero()
}