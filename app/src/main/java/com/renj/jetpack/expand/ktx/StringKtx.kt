package com.renj.jetpack.expand.ktx

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-19   10:31
 *
 * 描述：字符串扩展方法
 *
 * 修订历史：
 *
 * ======================================================================
 */

/**
 * 空处理
 */
fun String?.handlerString(): String {
    return this ?: ""
}

/**
 * 获取最后一个字符
 */
fun String?.lastChar(): Char {
    return if (isNullOrEmpty()) ' ' else this[length - 1]
}