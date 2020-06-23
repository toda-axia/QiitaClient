package com.wumpuss.qiitaclient.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.wumpuss.qiitaclient.BuildConfig
import com.wumpuss.qiitaclient.Pref
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.utils.AnalyticsUtils
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ConfirmDeleteListener {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getRecentArticle()
        viewModel.getAllTags()

        if (BuildConfig.BUILD_TYPE.equals("release")) {
            AnalyticsUtils.sendAppStartLog(baseContext)
        }

        fab.setOnClickListener {
            InputSearchTagDialog().show(supportFragmentManager, InputSearchTagDialog.TAG)
        }
    }

    override fun confirmDeletingBookmark(id: String) {
        viewModel.id = id
        ConfirmDeleteDialog().show(supportFragmentManager, "ConfirmDeleteDialog")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_auth, menu)
        menu.apply {
            findItem(R.id.menu_login).isVisible = Pref.accessToken.isBlank()
            findItem(R.id.menu_logout).isVisible = Pref.accessToken.isNotBlank()
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.apply {
            menu.findItem(R.id.menu_login).isVisible = Pref.accessToken.isBlank()
            menu.findItem(R.id.menu_logout).isVisible = Pref.accessToken.isNotBlank()
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_login -> {
                startActivityForResult(
                    Intent(this, LoginActivity::class.java),
                    MyPostArticleFragment.REQUEST_CODE_LOGIN
                )
                true
            }
            R.id.menu_logout -> {
                Pref.accessToken = ""
                viewModel.deleteMyPosts()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
