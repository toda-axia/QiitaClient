package com.wumpuss.qiitaclient.db

import androidx.room.*
import com.wumpuss.qiitaclient.data.QiitaBookmark

@Dao
interface QiitaBookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qiitaBookmark: QiitaBookmark)

    @Query("SELECT * FROM QiitaBookmark")
    suspend fun getBookmarks(): List<QiitaBookmark>

    @Query("SELECT * FROM QiitaBookmark WHERE id = :id")
    suspend fun isBookmark(id: String): QiitaBookmark

    @Query("DELETE FROM QiitaBookmark WHERE id = :id")
    suspend fun delete(id: String)
}