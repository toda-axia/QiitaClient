package com.wumpuss.qiitaclient.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.wumpuss.qiitaclient.data.QiitaBookmark
import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.data.QiitaTag
import com.wumpuss.qiitaclient.repository.QiitaRepository
import com.wumpuss.qiitaclient.utils.LoadStatus
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel(private val app: Application): ViewModel(), KoinComponent {
    private val repository: QiitaRepository by inject()
    val searchTag = MutableLiveData<String>()
    val bookmarkQiitaList = MutableLiveData<List<QiitaBookmark>>()
    val loadProgressStatus = MutableLiveData<LoadStatus>()
    val searchProgressStatus = MutableLiveData<LoadStatus>()
    val initialQiitaInfoList = MutableLiveData<List<QiitaInfo>>()
    val allTags = MutableLiveData<List<QiitaTag>>()
    val allMyPosts = MutableLiveData<List<QiitaInfo>>()
    var id = ""
    var title = ""
    var url = ""
    var profileImage = ""
    var tag = ""
    var isBookmark = false
    var page = 0

    fun getArticle(tag: String?) {
        searchTag.value = tag
    }
    val qiitaInfoList: LiveData<List<QiitaInfo>> = Transformations.switchMap(searchTag){ tag ->
        tag?.let {
            liveData {
                searchProgressStatus.value = LoadStatus.LOADING
                page++
                emit(repository.getArticle(it, page))
                searchProgressStatus.value = LoadStatus.LOADED
            }
        }
    }

    fun insertBookmark(qiitaBookmark: QiitaBookmark) {
        viewModelScope.launch {
            repository.insertQiitaBookmark(qiitaBookmark)
        }
    }

    fun getRecentArticle() {
        viewModelScope.launch {
            loadProgressStatus.value = LoadStatus.LOADING
            page++
            initialQiitaInfoList.value = repository.getRecentArticle(page)
            loadProgressStatus.value = LoadStatus.LOADED
        }
    }

    fun getBookmarks() {
        viewModelScope.launch {
            bookmarkQiitaList.value = repository.getBookmarks()
        }
    }

    fun isBookmark(id: String): Boolean {
        viewModelScope.launch {
            isBookmark = repository.isBookmark(id)
        }
        return isBookmark
    }

    fun deleteBookmark(id: String) {
        viewModelScope.launch {
            repository.deleteBookmark(id)
            getBookmarks()
        }
    }

    fun getAllTags() {
        viewModelScope.launch {
            allTags.value = repository.getAllTags()
        }
    }

    fun getMyPosts(token: String) {
        viewModelScope.launch {
            allMyPosts.value = repository.getMyPosts(token)
        }
    }
}