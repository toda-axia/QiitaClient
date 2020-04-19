package com.wumpuss.qiitaclient.service

import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.data.QiitaTag
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QiitaClientService {
    @GET("/api/v2/items?page=1&per_page=50")
    suspend fun getRecentItems(): Response<List<QiitaInfo>>
    @GET("/api/v2/tags/{tag}/items?page=1&per_page=50")
    suspend fun getItemsByTag(@Path("tag") tag: String): Response<List<QiitaInfo>>
    @GET("/api/v2/tags?sort=count")
    suspend fun getAllTags(): Response<List<QiitaTag>>
}