package com.wumpuss.qiitaclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.utils.LoadStatus
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_search_article.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchResultFragment : Fragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val qiitaAdapter: QiitaInfoListAdapter by lazy {
        QiitaInfoListAdapter(requireContext()) { bookmark ->
            startActivity(QiitaContentActivity.callingIntent(
                requireContext(),
                bookmark.id,
                bookmark.title,
                bookmark.url,
                bookmark.profileImage))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tag_text.text = viewModel.tag
        search_result_list.adapter = qiitaAdapter
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getArticle(viewModel.tag)
        bindViews()
    }

    private fun observeViewModel() {
        viewModel.qiitaInfoList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                qiitaAdapter.qiitaInfoList = it
            }
            qiitaAdapter.notifyDataSetChanged()
        })
    }

    private fun bindViews() {
        viewModel.searchProgressStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    LoadStatus.LOADING -> search_progress_container.visibility = View.VISIBLE
                    LoadStatus.LOADED -> search_progress_container.visibility = View.GONE
                }
            }
        })
    }
}
