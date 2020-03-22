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
        QiitaInfoListAdapter(requireContext()) {url ->
            startActivity(QiitaContentActivity.callingIntent(requireContext(), url))
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
}
