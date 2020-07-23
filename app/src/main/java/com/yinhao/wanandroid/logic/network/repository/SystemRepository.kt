package com.yinhao.wanandroid.logic.network.repository

import com.yinhao.wanandroid.logic.model.Result
import com.yinhao.wanandroid.logic.model.bean.KnowledgeTreeBean
import com.yinhao.wanandroid.logic.model.bean.NavigationBean
import com.yinhao.wanandroid.logic.network.BaseRepository
import com.yinhao.wanandroid.logic.network.ServiceCreator
import com.yinhao.wanandroid.logic.network.api.SystemService

/**
 * author:  yinhao
 * date:    2020/7/21
 * version: v1.0
 * ### description:
 */
object SystemRepository : BaseRepository() {
    private val service by lazy { ServiceCreator.getInstance().create(SystemService::class.java) }

    suspend fun getSystemData(): Result<List<KnowledgeTreeBean>> {
        return safeApiCall({ postSystemData() }, "获取体系数据失败")
    }

    private suspend fun postSystemData(): Result<List<KnowledgeTreeBean>> {
        return executeResponse(service.getSystemData())
    }
    suspend fun getNavData(): Result<List<NavigationBean>> {
        return safeApiCall({ postNavData() }, "获取导航数据失败")
    }

    private suspend fun postNavData(): Result<List<NavigationBean>> {
        return executeResponse(service.getNavData())
    }
}