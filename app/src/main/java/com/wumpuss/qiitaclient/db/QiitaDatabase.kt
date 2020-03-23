package com.wumpuss.qiitaclient.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wumpuss.qiitaclient.data.QiitaInfo

@Database(entities = [QiitaInfo::class], version = 1)

abstract class QiitaDatabase: RoomDatabase() {
    abstract fun QiitaInfoDao(): QiitaInfoDao
}