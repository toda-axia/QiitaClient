package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class QiitaContentActivity : AppCompatActivity() {
    companion object {
        private const val INTENT_EXTRA_URL = "INTENT_EXTRA_URL"
        fun callingIntent(context: Context, url: String) :Intent {
            val intent = Intent(context, QiitaContentActivity::class.java)
            intent.putExtra(INTENT_EXTRA_URL, url)
            return intent
        }
    }
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.articleUrl = intent.getStringExtra(INTENT_EXTRA_URL)
        setContentView(R.layout.activity_qiita_content)
    }
}
