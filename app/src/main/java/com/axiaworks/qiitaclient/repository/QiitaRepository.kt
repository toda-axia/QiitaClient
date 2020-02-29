package com.axiaworks.qiitaclient.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.axiaworks.qiitaclient.QiitaClientService
import com.axiaworks.qiitaclient.data.QiitaInfo
import com.axiaworks.qiitaclient.utils.LoadStatus
import org.koin.core.KoinComponent
import org.koin.core.inject

class QiitaRepository(private val context: Context): KoinComponent {
    private val qiitaApiService: QiitaClientService by inject()
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
        //var loadStatus = MutableLiveData<LoadStatus>()

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
}