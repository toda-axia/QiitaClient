package com.axiaworks.qiitaclient.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.axiaworks.qiitaclient.data.QiitaInfo
import com.axiaworks.qiitaclient.repository.QiitaRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel(private val app: Application): ViewModel(), KoinComponent {
    //private val repository: QiitaRepository by inject()
    private val repository: QiitaRepository by lazy {
        QiitaRepository(app)
    }

    val searchTag = MutableLiveData<String>()
    fun getArticle(tag: String?){
        searchTag.value = tag
    }
    val qiitaInfoList: LiveData<List<QiitaInfo>> = Transformations.switchMap(searchTag){ tag ->
        tag?.let {
            liveData {
                emit(repository.getArticle(it))
            }
        }
    }
}