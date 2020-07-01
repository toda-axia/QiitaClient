package com.wumpuss.qiitaclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_qiita_pager.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class QiitaPagerFragment : Fragment() {
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qiita_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tabArray = arrayListOf(
            getString(R.string.new_articles),
            getString(R.string.tag),
            getString(R.string.bookmark_articles),
            getString(R.string.my_post),
            "ストックした記事"
        )

        activity?.let {
            qiita_pager.adapter = QiitaPagerAdapter(it, tabArray)
        }

        TabLayoutMediator(qiita_tabs, qiita_pager) { tab, position ->
            tab.text = tabArray[position]
        }.attach()

    }
}

class QiitaPagerAdapter(fa: FragmentActivity, private val tabs: List<String>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewArticleFragment()
            1 -> QiitaTagsFragment()
            2 -> BookmarkArticleFragment()
            3 -> MyPostArticleFragment()
            4 -> StockArticlesFragment()
            else -> NewArticleFragment()
        }
    }
}
