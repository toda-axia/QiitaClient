package com.wumpuss.qiitaclient.repository

import android.content.Context
import android.widget.Toast
import com.wumpuss.qiitaclient.QiitaClientService
import com.wumpuss.qiitaclient.data.QiitaBookmark
import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.db.QiitaBookmarkDatabase
import org.koin.core.KoinComponent
import org.koin.core.inject

class QiitaRepository(private val context: Context): KoinComponent {
    private val qiitaApiService: QiitaClientService by inject()
    private val qiitaBookmarkDb: QiitaBookmarkDatabase by inject()

    suspend fun getRecentArticle(): List<QiitaInfo> {
        var returnList = emptyList<QiitaInfo>()

        runCatching {
            qiitaApiService.getRecentItems()
        }.onSuccess { response ->
            if (response.isSuccessful) {
                returnList = response.body()!!
            } else {
                Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
            }
        }.onFailure { e ->
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
                returnList = response.body()!!
            } else {
                Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
            }
        }.onFailure { e ->
            Toast.makeText(context, "Exception: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        return returnList
    }

    suspend fun insertQiitaBookmark(qiitaBookmark: QiitaBookmark) {
        qiitaBookmarkDb.qiitaBookmarkDao().insert(qiitaBookmark)
    }

    suspend fun getBookmarks(): List<QiitaBookmark> {
        var bookmarkList = emptyList<QiitaBookmark>()

        runCatching {
            qiitaBookmarkDb.qiitaBookmarkDao().getBookmarks()
        }.onSuccess {
            bookmarkList = it
        }.onFailure {
        }

        return bookmarkList
    }

    suspend fun deleteBookmark(id: String) {
        qiitaBookmarkDb.qiitaBookmarkDao().delete(id)
    }
}