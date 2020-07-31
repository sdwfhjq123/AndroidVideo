package com.yinhao.wanandroid.network.api

import com.yinhao.wanandroid.model.WanResponse
import com.yinhao.wanandroid.model.bean.ArticleListBean
import com.yinhao.wanandroid.model.bean.ProjectTreeBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * author:  yinhao
 * date:    2020/7/25
 * version: v1.0
 * ### description:
 */
interface ProjectService {
    @GET("project/tree/json")
    suspend fun getProjectCategoryList(): WanResponse<List<ProjectTreeBean>>

    @GET("project/list/{page}/json")
    suspend fun getProjectList(
        @Path("page") page: Int,
        @Query("cid") cId: Int
    ): WanResponse<ArticleListBean>
}