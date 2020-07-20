package com.yinhao.wanandroid.logic.network.api

import com.yinhao.wanandroid.logic.model.WanResponse
import com.yinhao.wanandroid.logic.model.bean.ArticleListBean
import com.yinhao.wanandroid.logic.model.bean.WXChapterBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * author:  yinhao
 * date:    2020/7/20
 * version: v1.0
 * ### description:
 */
interface WechatService {

    @GET("wxarticle/chapters/json")
    suspend fun getWxTabList(): WanResponse<List<WXChapterBean>>

    @GET("article/list/{page}/json")
    suspend fun getListByWx(@Path("page") page: Int, @Query("cid") cid: Int):WanResponse<ArticleListBean>
}