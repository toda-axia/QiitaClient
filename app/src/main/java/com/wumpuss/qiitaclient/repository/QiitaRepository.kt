package com.wumpuss.qiitaclient.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.wumpuss.qiitaclient.service.QiitaClientService
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.data.QiitaBookmark
import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.data.QiitaTag
import com.wumpuss.qiitaclient.data.UserCredential
import com.wumpuss.qiitaclient.db.QiitaBookmarkDatabase
import org.koin.core.KoinComponent
import org.koin.core.inject

class QiitaRepository(private val context: Context): KoinComponent {
    private val qiitaApiService: QiitaClientService by inject()
    private val qiitaBookmarkDb: QiitaBookmarkDatabase by inject()
    var returnRecentList = mutableListOf<QiitaInfo>()
    var returnSearchList = mutableListOf<QiitaInfo>()

    suspend fun getRecentArticle(page: Int): List<QiitaInfo> {

        runCatching {
            qiitaApiService.getRecentItems(page)
        }.onSuccess { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    it.forEach {
                        returnRecentList.add(it)
                    }
                }
            } else {
                Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
            }
        }.onFailure { e ->
            Toast.makeText(context, "Exception: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        return returnRecentList
    }

    suspend fun getArticle(searchTag: String, page: Int): List<QiitaInfo> {
        runCatching {
            qiitaApiService.getItemsByTag(searchTag, page)
        }.onSuccess { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    it.forEach {
                        returnSearchList.add(it)
                    }
                }
            } else {
                Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
            }
        }.onFailure {
            Toast.makeText(context, context.getString(R.string.search_article_error), Toast.LENGTH_SHORT).show()
        }

        return returnSearchList
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
            Toast.makeText(context, context.getString(R.string.save_article_error), Toast.LENGTH_SHORT).show()
        }

        return bookmarkList
    }

    suspend fun isBookmark(id: String): Boolean {
        var isBookmark = false
        runCatching {
            qiitaBookmarkDb.qiitaBookmarkDao().isBookmark(id)
        }.onSuccess {
            it?.let {
                isBookmark = true
            }
        }.onFailure {
            //isBookmark = false
        }
        return isBookmark
    }

    suspend fun deleteBookmark(id: String) {
        runCatching {
            qiitaBookmarkDb.qiitaBookmarkDao().delete(id)
        }.onFailure {
            Toast.makeText(context, context.getString(R.string.delete_bookmark_article_error), Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun getAllTags(): List<QiitaTag> {
        var allTagList = emptyList<QiitaTag>()
        runCatching {
            qiitaApiService.getAllTags()
        }.onSuccess { response ->
            if (response.isSuccessful) {
                allTagList = response.body()!!
            }
        }.onFailure {
            Toast.makeText(context, context.getString(R.string.get_tags_error), Toast.LENGTH_SHORT).show()
        }
        return allTagList
    }

    suspend fun getMyPosts(): List<QiitaInfo> {
        var allMyPosts = emptyList<QiitaInfo>()
        runCatching {
            qiitaApiService.getMyPosts(
                // accessToken = "Bearer ${userCredential.accessToken}"
                accessToken = "Bearer e3967e7dc8acf74970396214ecda735a31ba4837"
            )
        }.onSuccess { response ->
            if (response.isSuccessful) {
                allMyPosts= response.body()!!
                allMyPosts.forEach {
                    Log.d("デバッグ", it.toString())
                }
            } else {
                Toast.makeText(context, "アクセストークンが不正", Toast.LENGTH_SHORT).show()
            }
        }.onFailure {
            Toast.makeText(context, "自分の投稿の取得失敗", Toast.LENGTH_SHORT).show()
        }
        return allMyPosts
    }
}