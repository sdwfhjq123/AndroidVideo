package com.yinhao.wanandroid.logic.network.repository

import com.yinhao.wanandroid.logic.model.Result
import com.yinhao.wanandroid.logic.model.bean.ArticleBean
import com.yinhao.wanandroid.logic.model.bean.ArticleListBean
import com.yinhao.wanandroid.logic.model.bean.BannerBean
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

    suspend fun getHomeBanner(): Result<List<BannerBean>> {
        return safeApiCall(
            call = { requestHomeData() },
            errorMessage = "获取失败"
        )
    }

    suspend fun getArticleList(pageNum: Int): Result<ArticleListBean> {
        return safeApiCall(
            call = { requestArticleData(pageNum) },
            errorMessage = "获取失败"
        )
    }

    suspend fun getTopArticleList(): Result<List<ArticleBean>> {
        return safeApiCall(
            call = { requestTopArticleData() },
            errorMessage = "获取失败"
        )
    }

    private suspend fun requestHomeData(): Result<List<BannerBean>> {
        val response = homeService.getHomeBanner()
        return executeResponse(response)
    }

    private suspend fun requestArticleData(pageNum: Int): Result<ArticleListBean> {
        val request = homeService.getArticleList(pageNum)
        return executeResponse(request)
    }

    private suspend fun requestTopArticleData(): Result<List<ArticleBean>> {
        val request = homeService.getTopTipsArticleList()
        return executeResponse(request)
    }
}