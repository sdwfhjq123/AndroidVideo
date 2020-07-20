package com.yinhao.wanandroid.logic.model.bean

/**
 * author:  yinhao
 * date:    2020/7/20
 * version: v1.0
 * ### description:
 */

// 公众号列表实体
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