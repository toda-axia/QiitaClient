package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_bookmark_article.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class BookmarkArticleFragment : Fragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private var listener: ConfirmDeleteListener? = null
    private var lifecycleOwner: LifecycleOwner? = null
    private val qiitaBookmarkAdapter: QiitaBookmarkListAdapter by lazy {
        QiitaBookmarkListAdapter(requireContext(), listener) { bookmark ->
            startActivity(QiitaContentActivity.callingIntent(
                requireContext(),
                bookmark.id,
                bookmark.title,
                bookmark.url,
                bookmark.profileImage))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ConfirmDeleteListener
        lifecycleOwner = context as? LifecycleOwner
    }

    override fun onDetach() {
        listener = null
        lifecycleOwner = null
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark_article, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bookmark_title_list.adapter = qiitaBookmarkAdapter

        viewModel.bookmarkQiitaList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                if (list.size == 0) {
                    no_bookmark_text.visibility = View.VISIBLE
                } else {
                    no_bookmark_text.visibility = View.GONE
                }
                qiitaBookmarkAdapter.qiitaBookmarkList = it
            }
            qiitaBookmarkAdapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getBookmarks()
    }
}

interface ConfirmDeleteListener {
    fun confirmDeletingBookmark(id: String)
}