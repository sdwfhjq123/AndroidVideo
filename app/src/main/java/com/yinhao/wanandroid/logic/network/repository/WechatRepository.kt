package com.yinhao.wanandroid.logic.network.repository

import com.yinhao.wanandroid.logic.model.Result
import com.yinhao.wanandroid.logic.model.bean.ArticleListBean
import com.yinhao.wanandroid.logic.model.bean.WXChapterBean
import com.yinhao.wanandroid.logic.network.BaseRepository
import com.yinhao.wanandroid.logic.network.ServiceCreator
import com.yinhao.wanandroid.logic.network.api.WechatService

/**
 * author:  yinhao
 * date:    2020/7/20
 * version: v1.0
 * ### description:
 */
object WechatRepository : BaseRepository() {

    private val wechatService by lazy {
        ServiceCreator.getInstance().create(WechatService::class.java)
    }

    suspend fun getWxTabList(): Result<List<WXChapterBean>> {
        return safeApiCall(call = { requestWxTabList() }, errorMessage = "获取失败")
    }

    private suspend fun requestWxTabList(): Result<List<WXChapterBean>> {
        return executeResponse(wechatService.getWxTabList())
    }

    suspend fun getListByWx(page: Int, cid: Int): Result<ArticleListBean> {
        return safeApiCall(call = { requestListByWx(page, cid) }, errorMessage = "获取失败")
    }

    private suspend fun requestListByWx(page: Int, cid: Int): Result<ArticleListBean> {
        return executeResponse(wechatService.getListByWx(page, cid))
    }
}