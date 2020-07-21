package com.yinhao.wanandroid.logic.network.api

import com.yinhao.wanandroid.logic.model.WanResponse
import com.yinhao.wanandroid.logic.model.bean.KnowledgeTreeBean
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
}