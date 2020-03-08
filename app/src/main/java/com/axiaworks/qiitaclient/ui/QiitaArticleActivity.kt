package com.axiaworks.qiitaclient.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axiaworks.qiitaclient.R

class QiitaArticleActivity : AppCompatActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, QiitaArticleActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qiita_article)
    }
}
