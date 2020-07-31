package com.yinhao.wanandroid.network.api

import com.yinhao.wanandroid.model.WanResponse
import com.yinhao.wanandroid.model.bean.UserBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: DefaultAPI接口列表
 */
interface UserService {

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun signOn(
        @Field("username") signName: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): WanResponse<UserBean>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun signIn(
        @Field("username") signName: String,
        @Field("password") password: String
    ): WanResponse<UserBean>

}
