package com.wumpuss.qiitaclient.service

import com.wumpuss.qiitaclient.data.LoginRequest
import com.wumpuss.qiitaclient.data.ResponseToken
import retrofit2.http.*

interface LoginApi {
//    @Headers("Accept: application/json")
//    @FormUrlEncoded
//    @POST("/api/v2/access_tokens")
//    suspend fun token(
//        @Field("client_id") clientId: String,
//        @Field("client_secret") clientSecret: String,
//        @Field("code") code: String
//    ): ResponseToken
    @Headers("Content-type: application/json")
    @POST("/api/v2/access_tokens")
    suspend fun token(@Body request: LoginRequest): ResponseToken
}