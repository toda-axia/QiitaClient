package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchResultActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    companion object {
        private const val INTENT_EXTRA_TAG = "INTENT_EXTRA_TAG"
        fun callingIntent(context: Context, tag: String): Intent {
            val intent = Intent(context, SearchResultActivity::class.java)
            intent.putExtra(INTENT_EXTRA_TAG, tag)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tag = intent.getStringExtra(INTENT_EXTRA_TAG)!!

        viewModel.tag = tag

        setContentView(R.layout.activity_search_result)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_back, menu)

        val backMenuItem = menu.findItem(R.id.back_icon)
        backMenuItem.setOnMenuItemClickListener {
            this.finish()
            true
        }
        return true
    }
}
