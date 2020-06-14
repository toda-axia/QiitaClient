package com.wumpuss.qiitaclient.data

import com.squareup.moshi.Json
import java.io.Serializable

data class ResponseToken (
    @Json(name = "client_id") val clientId: String,
    val scopes: List<String>,
    val token: String
)