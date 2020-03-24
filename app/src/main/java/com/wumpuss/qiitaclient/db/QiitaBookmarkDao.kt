package com.wumpuss.qiitaclient.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.wumpuss.qiitaclient.data.QiitaBookmark

@Dao
interface QiitaBookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qiitaBookmark: QiitaBookmark)
}