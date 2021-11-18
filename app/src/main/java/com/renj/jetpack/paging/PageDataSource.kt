package com.renj.jetpack.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.renj.jetpack.R
import com.renj.jetpack.utils.ResUtils
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * ======================================================================
 *
 *
 * 作者：Renj
 *
 *
 * 创建时间：2021-11-17   16:14
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
class PageDataSource : PagingSource<Int, UserBean>() {
    override fun getRefreshKey(state: PagingState<Int, UserBean>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserBean> {
        val currentLoadPageKey = params.key ?: 1
        val userList = getUserList(currentLoadPageKey, params.loadSize)

        return LoadResult.Page(
            data = userList,
            prevKey = if (currentLoadPageKey == 1) null else currentLoadPageKey.minus(1),
            nextKey = if (currentLoadPageKey > 3) null else currentLoadPageKey.plus(1)
        )
    }

    private suspend fun getUserList(page: Int, loadSize: Int): List<UserBean> {
        // 主动延时，模仿网络耗时
        delay(1500)

        val result = ArrayList<UserBean>()
        for (i in 0..loadSize) {
            result.add(
                UserBean(
                    name = randomName((i + 1) * page),
                    age = randomAge(),
                    sex = randomSex(),
                    address = randomAddress(),
                    userHeader = randomImage(),
                    hobby = randomHobby(),
                    goodAt = randomGoodAt()
                )
            );
        }
        return result
    }

    private fun randomName(position: Int): String {
        val names: Array<String> = arrayOf("晓云", "花影", "皇甫清露", "上官静殊", "慕容飞絮", "司徒尘", "步非烟")
        return names[Random.nextInt(names.size)] + "-$position"
    }

    private fun randomAge(): Int {
        return Random.nextInt(18).plus(10)
    }

    private fun randomSex(): Int {
        return Random.nextInt(1)
    }

    private fun randomAddress(): String {
        val addresses: Array<String> =
            arrayOf("湖南省长沙市", "广东省深圳市", "浙江省杭州市", "广东省广州市", "北京", "上海", "江苏省苏州市")
        return addresses[Random.nextInt(addresses.size)]
    }

    private fun randomImage(): String {
        val images: Array<String> = arrayOf(
            ResUtils.getResUri(R.mipmap.ic_png1),
            ResUtils.getResUri(R.mipmap.ic_png2),
            ResUtils.getResUri(R.mipmap.ic_png3),
        )
        return images[Random.nextInt(images.size)]
    }

    private fun randomHobby(): String {
        return if (Random.nextBoolean()) "爱好爱好爱好爱好爱好爱好爱好爱好" else ""
    }

    private fun randomGoodAt(): String {
        return if (Random.nextBoolean()) "个人擅长" else ""
    }

}