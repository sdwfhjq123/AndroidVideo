package com.yinhao.wanandroid.logic.network.repository

import com.yinhao.wanandroid.logic.model.Result
import com.yinhao.wanandroid.logic.model.bean.SignOnBean
import com.yinhao.wanandroid.logic.network.BaseRepository
import com.yinhao.wanandroid.logic.network.ServiceCreator
import com.yinhao.wanandroid.logic.network.api.UserService

/**
 * author:  yinhao
 * date:    2020/7/6
 * version: v1.0
 * ### description: 登录注册注销工具类
 */
object UserRepository : BaseRepository() {
    private val userService = ServiceCreator.getInstance().create(UserService::class.java)

    suspend fun signOn(
        username: String,
        password: String,
        repeatPwd: String
    ): Result<SignOnBean> {
        return safeApiCall(
            call = { requestSignOn(username, password, repeatPwd) },
            errorMessage = "注册失败"
        )
    }

    suspend fun signIn(username: String, password: String): Result<SignOnBean> {
        return safeApiCall(
            call = { requestSignIn(username, password) },
            errorMessage = "登录失败"
        )
    }

    private suspend fun requestSignOn(
        username: String,
        password: String,
        repeatPwd: String
    ): Result<SignOnBean> {
        val response = userService.signOn(username, password, repeatPwd)
        return executeResponse(response)
    }

    private suspend fun requestSignIn(
        username: String,
        password: String
    ): Result<SignOnBean> {
        val response = userService.signIn(username, password)
        return executeResponse(response)
    }
}