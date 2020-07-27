package com.yinhao.wanandroid.logic.network.api

import com.yinhao.wanandroid.logic.model.WanResponse
import com.yinhao.wanandroid.logic.model.bean.ArticleBean
import com.yinhao.wanandroid.logic.model.bean.ArticleListBean
import com.yinhao.wanandroid.logic.model.bean.ProjectTreeBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author:  yinhao
 * date:    2020/7/25
 * version: v1.0
 * ### description:
 */
interface ProjectService {
    @GET("project/tree/json")
    suspend fun getProjectCategoryList(): WanResponse<List<ProjectTreeBean>>

    @GET("project/list/{page}/json?cid={cid}")
    suspend fun getProjectList(
        @Path("page") page: Int,
        @Path("cid") cId: Int
    ): WanResponse<ArticleListBean>
}