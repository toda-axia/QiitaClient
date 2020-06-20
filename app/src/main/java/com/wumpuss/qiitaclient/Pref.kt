package com.wumpuss.qiitaclient

import android.content.Context
import android.content.SharedPreferences

object Pref {
    const val QIITA_CLIENT = "qiita_client"
    private lateinit var context: Context
    var accessToken: String
    get(){
        return getString(Keys.ACCESS_TOKEN, "")
    }
    set(value){
        setString(Keys.ACCESS_TOKEN, value)
    }

    fun init(context: Context) {
        this.context = context
    }


    // Preferenceを取得
    private fun getPref(): SharedPreferences {
        return context.getSharedPreferences(QIITA_CLIENT, Context.MODE_PRIVATE)
    }

    // 値を編集する
    private fun getEditor(): SharedPreferences.Editor {
        return getPref().edit()
    }

    private fun setString(key: Keys, value: String) {
        getEditor().putString(key.name, value).commit()
    }

    private fun getString(key: Keys, default: String): String {
        return getPref().getString(key.name, default) ?: default
    }

    enum class Keys {
        ACCESS_TOKEN
    }
}