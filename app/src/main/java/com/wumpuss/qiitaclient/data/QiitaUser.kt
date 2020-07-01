package com.wumpuss.qiitaclient.data

import com.squareup.moshi.Json

data class QiitaUser (
    @Json(name = "id")val userId: String,
    val profile_image_url: String
)