package com.wumpuss.qiitaclient.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.wumpuss.qiitaclient.data.QiitaBookmark
import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.repository.QiitaRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel(private val app: Application): ViewModel(), KoinComponent {
    private val repository: QiitaRepository by inject()
    val searchTag = MutableLiveData<String>()
    val initialQiitaInfoList =  MutableLiveData<List<QiitaInfo>>()
    var id = ""
    var title = ""
    var url = ""
    var profileImage = ""
    fun getArticle(tag: String?) {
        searchTag.value = tag
    }
    val qiitaInfoList: LiveData<List<QiitaInfo>> = Transformations.switchMap(searchTag){ tag ->
        tag?.let {
            liveData {
                emit(repository.getArticle(it))
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
            initialQiitaInfoList.value = repository.getRecentArticle()
        }
    }
}