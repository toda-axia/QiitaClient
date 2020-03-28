package com.wumpuss.qiitaclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_search_article.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchArticleFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_search_article, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        search_title_list.adapter = qiitaAdapter
        search_button.setOnClickListener {
            viewModel.getArticle(input_search_tag.text.toString())
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        search_tag_text.text = viewModel.searchTag.value
        viewModel.qiitaInfoList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                qiitaAdapter.qiitaInfoList = it
            }
            qiitaAdapter.notifyDataSetChanged()
        })
    }
}
