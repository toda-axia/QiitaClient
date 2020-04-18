package com.wumpuss.qiitaclient.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.data.QiitaBookmark
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

        content_view.loadUrl(viewModel.url)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bookmark, menu)

        val bookmarkIcon = menu.findItem(R.id.bookmark_icon)
        if (viewModel.isBookmark) {
            bookmarkIcon.setIcon(R.drawable.ic_bookmark_black_24dp)
        } else {
            bookmarkIcon.setIcon(R.drawable.ic_bookmark_border_black_24dp)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bookmark_icon -> {
                viewModel.isBookmark = !viewModel.isBookmark
                requireActivity().invalidateOptionsMenu()
                if (viewModel.isBookmark) {
                    viewModel.insertBookmark(QiitaBookmark(
                        viewModel.id,
                        viewModel.title,
                        viewModel.url,
                        viewModel.profileImage
                    ))
                    Toast.makeText(context, getString(R.string.save_article_done), Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.deleteBookmark(viewModel.id)
                    Toast.makeText(context, getString(R.string.complete_delete_text), Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
