package com.yinhao.wanandroid.network.api

import com.yinhao.wanandroid.model.WanResponse
import com.yinhao.wanandroid.model.bean.KnowledgeTreeBean
import com.yinhao.wanandroid.model.bean.NavigationBean
import retrofit2.http.GET

/**
 * author:  yinhao
 * date:    2020/7/21
 * version: v1.0
 * ### description:
 */
interface SystemService {

    /**
     *  体系数据
     */
    @GET("tree/json")
    suspend fun getSystemData(): WanResponse<List<KnowledgeTreeBean>>

    @GET("navi/json")
    suspend fun getNavData(): WanResponse<List<NavigationBean>>
}