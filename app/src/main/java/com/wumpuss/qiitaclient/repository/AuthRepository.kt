package com.wumpuss.qiitaclient.repository

import android.content.Context
import android.widget.Toast
import com.wumpuss.qiitaclient.data.LoginRequest
import com.wumpuss.qiitaclient.service.QiitaClientService
import org.koin.core.KoinComponent
import org.koin.core.inject

class AuthRepository(private val context: Context): KoinComponent {
    private val qiitaApiService: QiitaClientService by inject()
    private var token: String = ""

    suspend fun token(
        clientId: String,
        clientSecret: String,
        code: String
    ): String {
        runCatching {
            val loginRequest = LoginRequest(clientId, clientSecret, code)
            qiitaApiService.token(loginRequest)
        }.onSuccess {
            token = it.token
        }.onFailure { e ->
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        return token
    }
}