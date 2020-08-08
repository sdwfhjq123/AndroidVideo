package com.yinhao.wanandroid.network.api

import com.yinhao.wanandroid.model.WanResponse
import com.yinhao.wanandroid.model.bean.BaseListResponseBody
import com.yinhao.wanandroid.model.bean.UserBean
import com.yinhao.wanandroid.model.bean.UserInfoBody
import com.yinhao.wanandroid.model.bean.UserScoreBean
import retrofit2.http.*

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

    //获取积分列表
    @GET("lg/coin/list/{page}/json")
    suspend fun getUserScoreList(@Path("page") page: Int): WanResponse<BaseListResponseBody<UserScoreBean>>

    @GET("lg/coin/userinfo/json")
    suspend fun getUserScore(): WanResponse<UserInfoBody>

}
