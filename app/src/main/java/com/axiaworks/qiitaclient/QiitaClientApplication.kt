package com.axiaworks.qiitaclient

import android.app.Application
import com.axiaworks.qiitaclient.repository.QiitaRepository
import com.axiaworks.qiitaclient.viewmodel.MainViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class QiitaClientApplication: Application() {
    companion object {
        const val BASE_URL = "https://qiita.com"
    }

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        val module: Module = module {
            viewModel{ MainViewModel(get()) }
            single {
                createApiService(QiitaClientService::class.java)
            }
            factory { QiitaRepository(get()) }
        }
        startKoin {
            androidContext(this@QiitaClientApplication)
            androidLogger()
            modules(module)
        }
    }

    private fun <T> createApiService(service: Class<T>): T =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            ))
            .build()
            .create(service)
}