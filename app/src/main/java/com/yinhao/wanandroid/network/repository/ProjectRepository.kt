package com.yinhao.wanandroid.network.repository

import com.yinhao.wanandroid.model.Result
import com.yinhao.wanandroid.model.bean.ArticleListBean
import com.yinhao.wanandroid.model.bean.ProjectTreeBean
import com.yinhao.wanandroid.network.BaseRepository
import com.yinhao.wanandroid.network.ServiceCreator
import com.yinhao.wanandroid.network.api.ProjectService

/**
 * author:  yinhao
 * date:    2020/7/25
 * version: v1.0
 * ### description:
 */
object ProjectRepository : BaseRepository() {
    private val service by lazy { ServiceCreator.getInstance().create(ProjectService::class.java) }

    suspend fun getProjectCategoryList(): Result<List<ProjectTreeBean>> {
        return safeApiCall { postProjectCategoryList() }
    }

    private suspend fun postProjectCategoryList(): Result<List<ProjectTreeBean>> {
        return executeResponse(service.getProjectCategoryList())
    }

    suspend fun getProjectList(page: Int, cId: Int): Result<ArticleListBean> {
        return safeApiCall { postProjectList(page, cId) }
    }

    private suspend fun postProjectList(page: Int, cId: Int): Result<ArticleListBean> {
        return executeResponse(service.getProjectList(page, cId))
    }
}