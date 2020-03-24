package com.wumpuss.qiitaclient.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wumpuss.qiitaclient.data.QiitaBookmark

@Database(
    entities = [QiitaBookmark::class],
    version = 1,
    exportSchema = false
)
abstract class QiitaBookmarkDatabase: RoomDatabase() {
    abstract fun qiitaBookmarkDao(): QiitaBookmarkDao
}