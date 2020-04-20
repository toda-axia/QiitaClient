package com.wumpuss.qiitaclient.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_qiita_tags.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class QiitaTagsFragment : Fragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val qiitaTagsAdapter: QiitaTagsAdapter by lazy {
        QiitaTagsAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qiita_tags, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        all_tags_list.adapter = qiitaTagsAdapter
        qiitaTagsAdapter.qiitaAllTags = viewModel.allTags

//        qiitaTagsAdapter.qiitaAllTags = viewModel.allTags
//        qiitaTagsAdapter.notifyDataSetChanged()

//        viewModel.allTags.observe(viewLifecycleOwner, Observer { list ->
//            list?.let {
//                qiitaTagsAdapter.qiitaAllTags = it
//            }
//            qiitaTagsAdapter.notifyDataSetChanged()
//        })
    }
}
