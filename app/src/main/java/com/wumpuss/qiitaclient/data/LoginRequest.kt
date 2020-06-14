package com.wumpuss.qiitaclient.data

import com.squareup.moshi.Json
import java.io.Serializable

data class LoginRequest(
    @Json(name = "client_id")val clientId: String,
    @Json(name = "client_secret")val clientSecret: String,
    val code: String
) : Serializable