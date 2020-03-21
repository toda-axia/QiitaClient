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
import kotlinx.android.synthetic.main.fragment_qiita_pager.*

class QiitaPagerFragment : Fragment() {
    companion object {
        private val tabArray = arrayListOf(
            "新着記事",
            "記事検索",
            "保存した記事"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qiita_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            qiita_pager.adapter = QiitaPagerAdapter(it)
        }

        TabLayoutMediator(qiita_tabs, qiita_pager) { tab, position ->
            tab.text = tabArray[position]
        }.attach()
    }
}

class QiitaPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    companion object {
        const val NUM_PAGES = 3
    }

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewArticleFragment()
            1 -> NewArticleFragment()
            2 -> NewArticleFragment()
            else -> NewArticleFragment()
        }
    }
}
