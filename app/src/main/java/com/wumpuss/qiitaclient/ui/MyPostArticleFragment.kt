package com.wumpuss.qiitaclient.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wumpuss.qiitaclient.Pref
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_my_post_article.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MyPostArticleFragment : Fragment() {
    companion object {
        const val REQUEST_CODE_LOGIN = 0x01
    }

    private val viewModel: MainViewModel by sharedViewModel()
    private val qiitaAdapter: MyPostArticleAdapter by lazy {
        MyPostArticleAdapter(requireContext()) { bookmark ->
            startActivity(QiitaContentActivity.callingIntent(
                requireContext(),
                bookmark.id,
                bookmark.title,
                bookmark.url,
                bookmark.profileImage
            ))
        }
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

        if (Pref.accessToken.isBlank()) {
            login_button.visibility = View.VISIBLE
            login_button.setOnClickListener {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_LOGIN)
            }
        }
    }

    private fun bindViews() {
        viewModel.isAccessToken.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    login_button.visibility = View.GONE
                } else {
                    no_my_post_text.visibility = View.GONE
                    login_button.visibility = View.VISIBLE
                    login_button.setOnClickListener {
                        val intent = Intent(requireContext(), LoginActivity::class.java)
                        startActivityForResult(intent, REQUEST_CODE_LOGIN)
                    }
                }
            }
        })

        viewModel.allMyPosts.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                if (list.isNotEmpty()) {
                    no_my_post_text.visibility = View.GONE
                } else {
                    if (Pref.accessToken.isNotBlank()) {
                        no_my_post_text.visibility = View.VISIBLE
                    }
                }
                qiitaAdapter.myPostArticleList = list
            }
            qiitaAdapter.notifyDataSetChanged()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_LOGIN) {
            when(resultCode) {
                Activity.RESULT_OK -> {
                    viewModel.getMyPosts(data?.getStringExtra("INPUT_ACCESS_TOKEN")!!)
                }
                else -> {
                    Toast.makeText(requireContext(), "ログインが完了しませんでした", Toast.LENGTH_LONG).show()
                    requireActivity().finish()
                }
            }
        }
    }
}
