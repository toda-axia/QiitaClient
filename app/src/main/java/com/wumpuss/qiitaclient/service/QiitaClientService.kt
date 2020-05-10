package com.wumpuss.qiitaclient.service

import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.data.QiitaTag
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface QiitaClientService {
    @GET("/api/v2/items?per_page=50")
    suspend fun getRecentItems(@Query("page") page: Int): Response<List<QiitaInfo>>
    @GET("/api/v2/tags/{tag}/items?per_page=50")
    suspend fun getItemsByTag(@Path("tag") tag: String, @Query("page") page: Int): Response<List<QiitaInfo>>
    @GET("/api/v2/tags?page=1&per_page=100&sort=count")
    suspend fun getAllTags(): Response<List<QiitaTag>>
    @GET("api/v2/authenticated_user/items")
    suspend fun getMyPosts(@Header("Authorization") accessToken: String): Response<List<QiitaInfo>>
}