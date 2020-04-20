package com.wumpuss.qiitaclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wumpuss.qiitaclient.BuildConfig
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
            AnalyticsUtils.sendGameStartLog(baseContext)
        }

        fab.setOnClickListener {
            InputSearchTagDialog().show(supportFragmentManager, InputSearchTagDialog.TAG)
        }
    }

    override fun confirmDeletingBookmark(id: String) {
        viewModel.id = id
        ConfirmDeleteDialog().show(supportFragmentManager, "ConfirmDeleteDialog")
    }
}
