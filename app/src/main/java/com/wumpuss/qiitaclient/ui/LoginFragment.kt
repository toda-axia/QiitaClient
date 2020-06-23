package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.wumpuss.qiitaclient.BuildConfig
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.databinding.FragmentLoginBinding
import com.wumpuss.qiitaclient.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {
    companion object {
        val TAG = LoginFragment::class.java.simpleName
    }
    private val viewModel: LoginViewModel by sharedViewModel()
    private var binding: FragmentLoginBinding? = null

    interface Callback {
        fun onAuthCompleted()
    }

    private var callback: Callback? = null

    fun requestAccessToken(code: String) {
        viewModel.requestAccessToken(
            BuildConfig.CLIENT_KEY,
            BuildConfig.CLIENT_SECRET,
            code
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Callback) {
            callback = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bindingData: FragmentLoginBinding? = DataBindingUtil.bind(view)
        binding = bindingData ?: return

        viewModel.accessToken.observe(viewLifecycleOwner, Observer{
            callback?.onAuthCompleted()
        })

        val authUri = Uri.parse("https://qiita.com")
            .buildUpon()
            .appendPath("api")
            .appendPath("v2")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter("client_id", BuildConfig.CLIENT_KEY)
            .appendQueryParameter("scope", "read_qiita")
            .appendQueryParameter("state", BuildConfig.CLIENT_SECRET)
            .build()

        val intent = Intent(Intent.ACTION_VIEW, authUri).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
        }
        startActivity(intent)
    }
}