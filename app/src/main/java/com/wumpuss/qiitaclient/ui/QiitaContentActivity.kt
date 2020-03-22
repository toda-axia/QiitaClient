package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wumpuss.qiitaclient.R

class QiitaContentActivity : AppCompatActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, QiitaContentActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qiita_content)
    }
}
