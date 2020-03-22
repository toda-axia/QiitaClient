package com.wumpuss.qiitaclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_qiita_content.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class QiitaContentFragment : Fragment() {
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qiita_content, container, false)
    }

//    override fun onResume() {
//        super.onResume()
//        content_view.loadUrl(viewModel.articleUrl)
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        content_view.loadUrl(viewModel.articleUrl)
    }
}
