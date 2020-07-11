package com.yinhao.wanandroid.logic.model.bean

/**
 * author:  yinhao
 * date:    2020/7/6
 * version: v1.0
 * ### description:
 */
data class SignOnBean(
    val admin: Boolean,
    val nickName: String,
    val email: String?,
    val publicName: String,
    val username: String
)