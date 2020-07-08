package com.yinhao.wanandroid.logic.network.repository

import com.yinhao.wanandroid.logic.model.Result
import com.yinhao.wanandroid.logic.network.BaseRepository
import com.yinhao.wanandroid.logic.network.ServiceCreator
import com.yinhao.wanandroid.logic.network.api.HomeService

/**
 * author:  yinhao
 * date:    2020/7/8
 * version: v1.0
 * ### description:
 */
object HomeRepository : BaseRepository() {
    private val homeService = ServiceCreator.getInstance().create(HomeService::class.java)

    suspend fun getHomeData(): Result<Any> {
        return safeApiCall(
            call = { requestHomeData() },
            errorMessage = "登录失败"
        )
    }

    private suspend fun requestHomeData(): Result<Any> {
        val response = homeService.getHomeArticle()
        return executeResponse(response)
    }
}