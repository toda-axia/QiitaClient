package com.wumpuss.qiitaclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
