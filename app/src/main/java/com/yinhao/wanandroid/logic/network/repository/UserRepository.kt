package com.yinhao.wanandroid.logic.network.repository

import com.yinhao.wanandroid.logic.model.Result
import com.yinhao.wanandroid.logic.model.bean.SignOnEntity
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
    ): Result<SignOnEntity> {
        return safeApiCall(
            call = { requestSignOn(username, password, repeatPwd) },
            errorMessage = "网络请求错误"
        )
    }

    private suspend fun requestSignOn(
        username: String,
        password: String,
        repeatPwd: String
    ): Result<SignOnEntity> {
        val response = userService.signOn(username, password, repeatPwd)
        return executeResponse(response)
    }

    private suspend fun requestSignIn(
        username: String,
        password: String
    ) {
//        val response= userService.sign
    }
}