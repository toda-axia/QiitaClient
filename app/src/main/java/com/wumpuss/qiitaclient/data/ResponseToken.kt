package com.wumpuss.qiitaclient.data

import com.squareup.moshi.Json

data class ResponseToken (
    @Json(name = "client_id") val clientId: String,
    val scopes: List<String>,
    val token: String
)