package com.renj.jetpack.paging

/**
 * ======================================================================
 *
 *
 * 作者：Renj
 *
 *
 * 创建时间：2021-11-17   11:48
 *
 *
 * 描述：
 *
 *
 * 修订历史：
 *
 *
 * ======================================================================
 */
data class UserBean(
    var name: String,
    var age: Int,
    var sex: Int,
    var address: String,
    var userHeader: String,
    var hobby: String? = null,
    var goodAt: String? = null
)