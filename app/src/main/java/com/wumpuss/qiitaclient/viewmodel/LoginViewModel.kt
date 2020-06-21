package com.wumpuss.qiitaclient.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wumpuss.qiitaclient.Pref
import com.wumpuss.qiitaclient.repository.AuthRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginViewModel(app: Application): ViewModel(), KoinComponent {
    private val authRepository: AuthRepository by inject()
    val accessToken = MutableLiveData<String>()

    fun requestAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ) {
        viewModelScope.launch {
            val token = authRepository.getToken(
                clientId,
                clientSecret,
                code
            )
            accessToken.value = token
            Pref.accessToken = token
        }
    }

    fun deleteToken(token: String) {
        viewModelScope.launch {
            authRepository.deleteToken(token)
        }
    }
}