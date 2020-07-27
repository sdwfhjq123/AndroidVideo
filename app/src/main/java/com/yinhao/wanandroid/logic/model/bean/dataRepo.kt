package com.yinhao.wanandroid.logic.model.bean

import java.io.Serializable

/**
 * author:  yinhao
 * date:    2020/7/21
 * version: v1.0
 * ### description:
 */

/**
 * 文章
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

data class ArticleBean(
    val id: Int,
    val originId: Int,
    val title: String,
    val chapterId: Int,
    val chapterName: String,
    val envelopePic: String,
    val link: String,
    val author: String,
    val origin: String,
    val publishTime: Long,
    val zan: Int,
    val desc: String,
    val visible: Int,
    val niceDate: String,
    val niceShareDate: String,
    val courseId: Int,
    var collect: Boolean,
    val apkLink: String,
    val projectLink: String,
    val superChapterId: Int,
    val superChapterName: String?,
    val type: Int,
    val fresh: Boolean,
    val audit: Int,
    val prefix: String,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val tags: List<Tag>, // Not sure
    val userId: Int,
    var top: Int
)

data class Tag(val name: String, val url: String)

/**
 * 轮播图
 */
data class BannerBean(
    val desc: String,
    val id: Long,
    val isVisible: Int,
    val order: Int,
    val type: Int,
    val url: String,
    val imagePath: String
)

/**
 * 公众号列表实体
 */
data class WXChapterBean(
    val children: List<String>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)

/**
 * 知识体系
 */
data class KnowledgeTreeBean(
    val children: MutableList<Knowledge>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
) : Serializable

data class Knowledge(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
) : Serializable


// 导航
data class NavigationBean(
    val articles: MutableList<ArticleBean>,
    val cid: Int,
    val name: String
)

// 项目
data class ProjectTreeBean(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
)