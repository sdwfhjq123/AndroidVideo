package com.yinhao.wanandroid.network.repository

import com.yinhao.wanandroid.model.Result
import com.yinhao.wanandroid.model.bean.ArticleListBean
import com.yinhao.wanandroid.network.BaseRepository
import com.yinhao.wanandroid.network.ServiceCreator
import com.yinhao.wanandroid.network.api.SquareService

/**
 * author:  yinhao
 * date:    2020/7/20
 * version: v1.0
 * ### description:
 */
object SquareRepository : BaseRepository() {
    private val squareService = ServiceCreator.getInstance().create(SquareService::class.java)

    suspend fun getSquareList(pageNum: Int): Result<ArticleListBean> {
        return safeApiCall(call = { requestSquareList(pageNum) })
    }

    private suspend fun requestSquareList(pageNum: Int): Result<ArticleListBean> {
        val request = squareService.getSquareList(pageNum)
        return executeResponse(request)
    }


}