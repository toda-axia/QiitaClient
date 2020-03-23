package com.wumpuss.qiitaclient.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

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
        this.setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qiita_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        content_view.loadUrl(viewModel.articleUrl)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_content, menu)

        val addMenuItem = menu.findItem(R.id.munu_add)
        addMenuItem.setOnMenuItemClickListener {
            viewModel.saveArticle(viewModel.)
        }
    }
}
