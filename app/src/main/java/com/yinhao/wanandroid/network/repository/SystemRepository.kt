package com.yinhao.wanandroid.network.repository

import com.yinhao.wanandroid.model.Result
import com.yinhao.wanandroid.model.bean.KnowledgeTreeBean
import com.yinhao.wanandroid.model.bean.NavigationBean
import com.yinhao.wanandroid.network.BaseRepository
import com.yinhao.wanandroid.network.ServiceCreator
import com.yinhao.wanandroid.network.api.SystemService

/**
 * author:  yinhao
 * date:    2020/7/21
 * version: v1.0
 * ### description:
 */
object SystemRepository : BaseRepository() {
    private val service by lazy { ServiceCreator.getInstance().create(SystemService::class.java) }

    suspend fun getSystemData(): Result<List<KnowledgeTreeBean>> {
        return safeApiCall { postSystemData() }
    }

    private suspend fun postSystemData(): Result<List<KnowledgeTreeBean>> {
        return executeResponse(service.getSystemData())
    }
    suspend fun getNavData(): Result<List<NavigationBean>> {
        return safeApiCall { postNavData() }
    }

    private suspend fun postNavData(): Result<List<NavigationBean>> {
        return executeResponse(service.getNavData())
    }
}