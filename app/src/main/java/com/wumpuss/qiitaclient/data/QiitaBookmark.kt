package com.wumpuss.qiitaclient.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QiitaBookmark (
    @PrimaryKey
    val id: String,
    val title: String,
    val url: String,
    val profileImage: String
)