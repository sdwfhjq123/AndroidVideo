package com.yinhao.wanandroid.network.api

import com.yinhao.wanandroid.model.WanResponse
import com.yinhao.wanandroid.model.bean.ArticleBean
import com.yinhao.wanandroid.model.bean.ArticleListBean
import com.yinhao.wanandroid.model.bean.BannerBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author:  yinhao
 * date:    2020/7/8
 * version: v1.0
 * ### description:
 */
interface HomeService {
    /**
     * 轮播图
     */
    @GET("banner/json")
    suspend fun getHomeBanner(): WanResponse<List<BannerBean>>

    /**
     * 获取首页文章列表
     */
    @GET("article/list/{pageNum}/json")
    suspend fun getArticleList(@Path("pageNum") pageNum: Int = 0): WanResponse<ArticleListBean>

    @GET("article/top/json")
    suspend fun getTopTipsArticleList(): WanResponse<List<ArticleBean>>
}
