package com.wumpuss.qiitaclient.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.wumpuss.qiitaclient.QiitaClientService
import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.db.QiitaDatabase
import com.wumpuss.qiitaclient.utils.LoadStatus
import org.koin.core.KoinComponent
import org.koin.core.inject

class QiitaRepository(private val context: Context): KoinComponent {
    private val qiitaApiService: QiitaClientService by inject()
    private val qiitaDb: QiitaDatabase by inject()
    val loadStatus = MutableLiveData<LoadStatus>()

    suspend fun getRecentArticle(): List<QiitaInfo> {
        var returnList = emptyList<QiitaInfo>()

        runCatching {
            qiitaApiService.getRecentItems()
        }.onSuccess { response ->
            if (response.isSuccessful) {
                loadStatus.value = LoadStatus.DONE
                returnList = response.body()!!
            } else {
                loadStatus.value = LoadStatus.ERROR
                Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
            }
        }.onFailure { e ->
            loadStatus.value = LoadStatus.ERROR
            Toast.makeText(context, "Exception: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        return returnList
    }

    suspend fun getArticle(searchTag: String): List<QiitaInfo> {
        var returnList = emptyList<QiitaInfo>()

        runCatching {
            qiitaApiService.getItemsByTag(searchTag)
        }.onSuccess { response ->
            if (response.isSuccessful) {
                loadStatus.value = LoadStatus.DONE
                returnList = response.body()!!
            } else {
                loadStatus.value = LoadStatus.ERROR
                Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
            }
        }.onFailure { e ->
            loadStatus.value = LoadStatus.ERROR
            Toast.makeText(context, "Exception: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        return returnList
    }

    suspend fun saveArticle(qiitaInfo: QiitaInfo) {
        qiitaDb.QiitaInfoDao().insert(qiitaInfo)
    }
}