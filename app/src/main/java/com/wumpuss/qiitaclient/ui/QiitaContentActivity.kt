package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class QiitaContentActivity : AppCompatActivity() {
    companion object {
        private const val INTENT_EXTRA_ID = "INTENT_EXTRA_ID"
        private const val INTENT_EXTRA_TITLE = "INTENT_EXTRA_TITLE"
        private const val INTENT_EXTRA_URL = "INTENT_EXTRA_URL"
        private const val INTENT_EXTRA_PROFILE_IMAGE = "INTENT_PROFILE_IMAGE"
        fun callingIntent(context: Context, id: String, title: String, url: String, profileImage: String) :Intent {
            val intent = Intent(context, QiitaContentActivity::class.java)
            intent.putExtra(INTENT_EXTRA_ID, id)
            intent.putExtra(INTENT_EXTRA_TITLE, title)
            intent.putExtra(INTENT_EXTRA_URL, url)
            intent.putExtra(INTENT_EXTRA_PROFILE_IMAGE, profileImage)
            return intent
        }
    }
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.id = intent.getStringExtra(INTENT_EXTRA_ID)!!
        viewModel.title = intent.getStringExtra(INTENT_EXTRA_TITLE)!!
        viewModel.url = intent.getStringExtra(INTENT_EXTRA_URL)!!
        viewModel.profileImage = intent.getStringExtra(INTENT_EXTRA_PROFILE_IMAGE)!!
        viewModel.isBookmark = viewModel.isBookmark(viewModel.id)

        setContentView(R.layout.activity_qiita_content)
    }
}
