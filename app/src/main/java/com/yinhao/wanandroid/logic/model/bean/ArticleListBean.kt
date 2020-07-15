package com.yinhao.wanandroid.logic.model.bean

import java.io.Serializable

/**
 * Created by luyao
 * on 2018/3/13 14:48
 */
data class ArticleListBean(
    val offset: Int,
    val size: Int,
    val total: Int,
    val pageCount: Int,
    val curPage: Int,
    val over: Boolean,
    val datas: List<ArticleBean>
) : Serializable