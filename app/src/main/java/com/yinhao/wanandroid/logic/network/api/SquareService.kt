package com.yinhao.wanandroid.logic.network.api

import com.yinhao.wanandroid.logic.model.WanResponse
import com.yinhao.wanandroid.logic.model.bean.ArticleListBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author:  yinhao
 * date:    2020/7/20
 * version: v1.0
 * ### description:
 */
interface SquareService {

    @GET("user_article/list/{pageNum}/json")
    suspend fun getSquareList(@Path("pageNum") pageNum: Int): WanResponse<ArticleListBean>
}