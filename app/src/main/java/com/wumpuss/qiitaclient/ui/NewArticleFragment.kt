package com.wumpuss.qiitaclient.ui


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_new_article.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class NewArticleFragment : Fragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val qiitaAdapter: QiitaInfoListAdapter by lazy {
        QiitaInfoListAdapter(requireContext()) {
            viewModel.articleUrl = it
            QiitaArticleFragment().show(parentFragmentManager, "QiitaArticleFragment")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_article, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(new_title_list) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = qiitaAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRecentArticle()
        viewModel.initialQiitaInfoList.observe(this, Observer { list ->
            list?.let {
                qiitaAdapter.qiitaInfoList = it
            }
            qiitaAdapter.notifyDataSetChanged()
        })
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//
//        inflater.inflate(R.menu.menu_search, menu)
//
//        val searchMenuItem = menu.findItem(R.id.search_item)
//        val searchView = searchMenuItem.actionView as SearchView
//
//        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(tag: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextSubmit(tag: String?): Boolean {
//                tag?.let {
//                    viewModel.getArticle(it)
//                    observeViewModel()
//                    //text_search_word.text = "検索ワード: ${viewModel.searchTag.value}"
//                }
//                return true
//            }
//        })
//    }

//    private fun observeViewModel() {
//        viewModel.qiitaInfoList.observe(this, Observer { list ->
//            list?.let {
//                qiitaAdapter.qiitaInfoList = it
//            }
//            qiitaAdapter.notifyDataSetChanged()
//        })
//    }
}
