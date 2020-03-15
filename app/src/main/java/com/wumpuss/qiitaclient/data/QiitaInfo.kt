package com.wumpuss.qiitaclient.data

import com.squareup.moshi.Json

data class QiitaInfo (
    val id: String,
    val title: String,
    val body: String,
    val url: String,
    @Json(name = "user")val qiitaUser: QiitaUser
)