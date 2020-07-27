package com.yinhao.wanandroid.logic.network.repository

import com.yinhao.wanandroid.logic.model.Result
import com.yinhao.wanandroid.logic.model.bean.ArticleBean
import com.yinhao.wanandroid.logic.model.bean.ArticleListBean
import com.yinhao.wanandroid.logic.model.bean.ProjectTreeBean
import com.yinhao.wanandroid.logic.network.BaseRepository
import com.yinhao.wanandroid.logic.network.ServiceCreator
import com.yinhao.wanandroid.logic.network.api.ProjectService

/**
 * author:  yinhao
 * date:    2020/7/25
 * version: v1.0
 * ### description:
 */
object ProjectRepository : BaseRepository() {
    private val service by lazy { ServiceCreator.getInstance().create(ProjectService::class.java) }

    suspend fun getProjectCategoryList(): Result<List<ProjectTreeBean>> {
        return safeApiCall({ postProjectCategoryList() }, "获取导航种类失败")
    }

    private suspend fun postProjectCategoryList(): Result<List<ProjectTreeBean>> {
        return executeResponse(service.getProjectCategoryList())
    }

    suspend fun getProjectList(page: Int, cId: Int): Result<ArticleListBean> {
        return safeApiCall({ postProjectList(page, cId) }, "获取导航列表失败")
    }

    private suspend fun postProjectList(page: Int, cId: Int): Result<ArticleListBean> {
        return executeResponse(service.getProjectList(page, cId))
    }
}