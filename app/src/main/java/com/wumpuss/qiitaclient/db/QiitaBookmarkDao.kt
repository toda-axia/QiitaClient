package com.wumpuss.qiitaclient.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wumpuss.qiitaclient.data.QiitaBookmark

@Dao
interface QiitaBookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qiitaBookmark: QiitaBookmark)

    @Query("SELECT * FROM QiitaBookmark")
    suspend fun getBookmarks(): List<QiitaBookmark>
}