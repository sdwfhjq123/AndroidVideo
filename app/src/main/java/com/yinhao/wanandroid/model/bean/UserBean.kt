package com.yinhao.wanandroid.model.bean


/**
 * author:  yinhao
 * date:    2020/7/6
 * version: v1.0
 * ### description:
 */
data class UserBean(
    val admin: Boolean,
    val nickName: String,
    val email: String?,
    val publicName: String,
    val username: String
)