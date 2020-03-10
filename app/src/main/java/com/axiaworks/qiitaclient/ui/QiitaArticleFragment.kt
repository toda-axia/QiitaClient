package com.axiaworks.qiitaclient.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.axiaworks.qiitaclient.R
import com.axiaworks.qiitaclient.databinding.FragmentQiitaArticleBinding
import com.axiaworks.qiitaclient.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class QiitaArticleFragment : DialogFragment() {
    private lateinit var binding: FragmentQiitaArticleBinding
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_qiita_article,
            null,
            false
        )
        return Dialog(requireContext()).apply {
            setContentView(binding.root)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.articleView.loadUrl(viewModel.articleUrl.value!!)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.articleView.loadUrl("https://www.google.com/")
//    }
}
