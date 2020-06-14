package com.wumpuss.qiitaclient.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wumpuss.qiitaclient.repository.AuthRepository
import com.wumpuss.qiitaclient.repository.QiitaRepository
import com.wumpuss.qiitaclient.utils.LoadStatus
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginViewModel(application: Application): ViewModel(), KoinComponent {
    private val authRepository: AuthRepository by inject()
    val accessToken = MutableLiveData<String>("")
    var token = ""

    fun requestAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ) {
        viewModelScope.launch {
            accessToken.value = authRepository.token(
                clientId,
                clientSecret,
                code
            )
        }
    }
}