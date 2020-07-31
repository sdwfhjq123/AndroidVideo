package com.yinhao.wanandroid.network.repository

import com.yinhao.wanandroid.model.Result
import com.yinhao.wanandroid.model.bean.ArticleListBean
import com.yinhao.wanandroid.model.bean.WXChapterBean
import com.yinhao.wanandroid.network.BaseRepository
import com.yinhao.wanandroid.network.ServiceCreator
import com.yinhao.wanandroid.network.api.WechatService

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
        return safeApiCall(call = { requestWxTabList() } )
    }

    private suspend fun requestWxTabList(): Result<List<WXChapterBean>> {
        return executeResponse(wechatService.getWxTabList())
    }

    suspend fun getArticleHistoryByWx(id: Int, page: Int): Result<ArticleListBean> {
        return safeApiCall(call = { requestArticleHistoryByWx(id, page) })
    }

    private suspend fun requestArticleHistoryByWx(id: Int, page: Int): Result<ArticleListBean> {
        return executeResponse(wechatService.getArticleHistoryByWx(id, page))
    }
}