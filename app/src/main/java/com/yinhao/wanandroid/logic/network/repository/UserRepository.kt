package com.yinhao.wanandroid.logic.network.repository

import com.yinhao.commonmodule.base.base.BaseRepository
import com.yinhao.commonmodule.base.repository.RepositoryResult
import com.yinhao.wanandroid.logic.model.SignOnEntity
import com.yinhao.wanandroid.logic.network.ServiceCreator
import com.yinhao.wanandroid.logic.network.api.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
    ): RepositoryResult<SignOnEntity> {
       return userService.signOn(username, password, repeatPwd)
    }
}