package com.yinhao.wanandroid.network.api

import com.yinhao.wanandroid.model.WanResponse
import com.yinhao.wanandroid.model.bean.ArticleListBean
import com.yinhao.wanandroid.model.bean.WXChapterBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author:  yinhao
 * date:    2020/7/20
 * version: v1.0
 * ### description:
 */
interface WechatService {

    @GET("wxarticle/chapters/json")
    suspend fun getWxTabList(): WanResponse<List<WXChapterBean>>

    @GET("wxarticle/list/{id}/{page}/json")
    suspend fun getArticleHistoryByWx(
        @Path("id") id: Int,
        @Path("page") page: Int
    ): WanResponse<ArticleListBean>
}