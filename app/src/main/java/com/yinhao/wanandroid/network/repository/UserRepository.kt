package com.yinhao.wanandroid.network.repository

import com.yinhao.wanandroid.db.AppDatabase
import com.yinhao.wanandroid.db.entity.User
import com.yinhao.wanandroid.model.Result
import com.yinhao.wanandroid.model.bean.UserBean
import com.yinhao.wanandroid.network.BaseRepository
import com.yinhao.wanandroid.network.ServiceCreator
import com.yinhao.wanandroid.network.api.UserService

/**
 * author:  yinhao
 * date:    2020/7/6
 * version: v1.0
 * ### description: 登录注册注销工具类
 */
object UserRepository : BaseRepository() {
    private val userService = ServiceCreator.getInstance().create(UserService::class.java)

    suspend fun setUser(bean: UserBean) {
        val user = User(bean.username)
        user.email = bean.email
        user.nikeName = bean.nickName
        user.publicName = bean.publicName
        AppDatabase.getDatabase().userDao().insertUser(user)
    }

    suspend fun deleteUser(){
       return AppDatabase.getDatabase().userDao().deleteUser()
    }

    suspend fun getUser(): List<User> {
        return AppDatabase.getDatabase().userDao().getAll()
    }

    suspend fun signOn(
        username: String,
        password: String,
        repeatPwd: String
    ): Result<UserBean> {
        return safeApiCall(
            call = { requestSignOn(username, password, repeatPwd) }
        )
    }

    suspend fun signIn(username: String, password: String): Result<UserBean> {
        return safeApiCall(
            call = { requestSignIn(username, password) }
        )
    }

    private suspend fun requestSignOn(
        username: String,
        password: String,
        repeatPwd: String
    ): Result<UserBean> {
        val response = userService.signOn(username, password, repeatPwd)
        return executeResponse(response)
    }

    private suspend fun requestSignIn(
        username: String,
        password: String
    ): Result<UserBean> {
        val response = userService.signIn(username, password)
        return executeResponse(response)
    }
}