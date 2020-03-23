package com.wumpuss.qiitaclient.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.wumpuss.qiitaclient.data.QiitaInfo

@Dao
interface QiitaInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qiitaInfo: QiitaInfo)
}