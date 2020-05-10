package com.wumpuss.qiitaclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_my_post_article.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MyPostArticleFragment : Fragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val qiitaAdapter: MyPostArticleAdapter by lazy {
        MyPostArticleAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_post_article, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mypost_article_list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = qiitaAdapter
        }

        bindViews()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getMyPosts()
    }

    private fun bindViews() {
        viewModel.allMyPosts.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                qiitaAdapter.myPostArticleList = list
            }
            qiitaAdapter.notifyDataSetChanged()
        })
    }
}
