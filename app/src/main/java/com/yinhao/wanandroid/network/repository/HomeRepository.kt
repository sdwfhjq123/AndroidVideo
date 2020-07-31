package com.yinhao.wanandroid.network.repository

import com.yinhao.wanandroid.model.Result
import com.yinhao.wanandroid.model.bean.ArticleBean
import com.yinhao.wanandroid.model.bean.ArticleListBean
import com.yinhao.wanandroid.model.bean.BannerBean
import com.yinhao.wanandroid.network.BaseRepository
import com.yinhao.wanandroid.network.ServiceCreator
import com.yinhao.wanandroid.network.api.HomeService

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
            call = { requestHomeData() }
        )
    }

    suspend fun getArticleList(pageNum: Int): Result<ArticleListBean> {
        return safeApiCall(
            call = { requestArticleData(pageNum) }
        )
    }

    suspend fun getTopArticleList(): Result<List<ArticleBean>> {
        return safeApiCall(
            call = { requestTopArticleData() }
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