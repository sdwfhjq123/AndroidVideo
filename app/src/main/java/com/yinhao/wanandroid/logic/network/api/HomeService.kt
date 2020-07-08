package com.yinhao.wanandroid.logic.network.api

import com.yinhao.wanandroid.logic.model.WanResponse
import retrofit2.http.GET

/**
 * author:  yinhao
 * date:    2020/7/8
 * version: v1.0
 * ### description:
 */
interface HomeService {

    /**
     * 注册
     */
    @GET("lg/todo/listnotdo/0/json/1")
    suspend fun getHomeArticle(): WanResponse<Any>

}
