package com.wumpuss.qiitaclient.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.utils.LoadStatus
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_new_article.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class NewArticleFragment : Fragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val qiitaAdapter: QiitaInfoListAdapter by lazy {
        QiitaInfoListAdapter(requireContext()) { bookmark ->
            startActivity(QiitaContentActivity.callingIntent(
                requireContext(),
                bookmark.id,
                bookmark.title,
                bookmark.url,
                bookmark.profileImage)
            )
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

        val lm = LinearLayoutManager(requireContext())

        new_title_list.apply {
            layoutManager = lm
            adapter = qiitaAdapter
            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val childCount = qiitaAdapter.itemCount
                        val lastPosition = lm.findLastCompletelyVisibleItemPosition()
                        if (childCount - 1 == lastPosition) {
                            viewModel.getRecentArticle()
                        }
                    }
                }
            })
        }

        viewModel.initialQiitaInfoList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                qiitaAdapter.qiitaInfoList = it
            }
            qiitaAdapter.notifyDataSetChanged()
        })

        bindViews()

        swipe_to_refresh_qiita_client.setOnRefreshListener{
            swipe_to_refresh_qiita_client.isRefreshing = false
            viewModel.getRecentArticle()
        }
    }

    private fun bindViews() {
        viewModel.loadProgressStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    LoadStatus.LOADING -> load_progress_container.visibility = View.VISIBLE
                    LoadStatus.LOADED -> load_progress_container.visibility = View.GONE
                }
            }
        })
    }
}
