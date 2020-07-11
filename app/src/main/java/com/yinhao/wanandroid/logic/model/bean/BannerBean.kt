package com.yinhao.wanandroid.logic.model.bean

/**
 * author:  yinhao
 * date:    2020/7/11
 * version: v1.0
 * ### description:
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