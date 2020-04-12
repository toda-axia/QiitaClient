package com.wumpuss.qiitaclient.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.wumpuss.qiitaclient.data.QiitaBookmark
import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.repository.QiitaRepository
import com.wumpuss.qiitaclient.utils.LoadStatus
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel(private val app: Application): ViewModel(), KoinComponent {
    private val repository: QiitaRepository by inject()
    val searchTag = MutableLiveData<String>()
    val initialQiitaInfoList =  MutableLiveData<List<QiitaInfo>>()
    val searchResultQiitaInfoList = MutableLiveData<List<QiitaInfo>>()
    val bookmarkQiitaList = MutableLiveData<List<QiitaBookmark>>()
    val loadProgressStatus = MutableLiveData<LoadStatus>()
    val searchProgressStatus = MutableLiveData<LoadStatus>()
    var id = ""
    var title = ""
    var url = ""
    var profileImage = ""
    var tag = ""

    fun getArticle(tag: String?) {
        searchTag.value = tag
    }
    val qiitaInfoList: LiveData<List<QiitaInfo>> = Transformations.switchMap(searchTag){ tag ->
        tag?.let {
            liveData {
                searchProgressStatus.value = LoadStatus.LOADING
                emit(repository.getArticle(it))
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
            initialQiitaInfoList.value = repository.getRecentArticle()
            loadProgressStatus.value = LoadStatus.LOADED
        }
    }

//    fun getArticleByTag(tag: String) {
//        viewModelScope.launch {
//            loadProgressStatus.value = LoadStatus.LOADING
//            searchResultQiitaInfoList.value = repository.getArticle(tag)
//            loadProgressStatus.value = LoadStatus.LOADED
//        }
//    }

    fun getBookmarks() {
        viewModelScope.launch {
            bookmarkQiitaList.value = repository.getBookmarks()
        }
    }

    fun deleteBookmark(id: String) {
        viewModelScope.launch {
            repository.deleteBookmark(id)
            getBookmarks()
        }
    }
}