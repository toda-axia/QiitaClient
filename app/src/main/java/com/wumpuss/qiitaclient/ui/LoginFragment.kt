package com.wumpuss.qiitaclient.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.databinding.FragmentLoginBinding
import com.wumpuss.qiitaclient.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    companion object {
        val TAG = LoginFragment::class.java.simpleName
    }

    private val viewModel: LoginViewModel by viewModel()
    private var binding: FragmentLoginBinding? = null


    private val onObtainCode = fun(code: String) {
        viewModel.requestAccessToken(
            "d0a9a6eefab7ea557f0f2c95a678f2e913bf0c43",
            "68d2c388302b56f04f381fc332c111fea8eb32a9",
            code
        )
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

        val authUri = Uri.parse("https://qiita.com")
            .buildUpon()
            .appendPath("api")
            .appendPath("v2")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter("client_id", "d0a9a6eefab7ea557f0f2c95a678f2e913bf0c43")
            .appendQueryParameter("scope", "read_qiita")
            .appendQueryParameter("state", "68d2c388302b56f04f381fc332c111fea8eb32a9")
            .build()

        bindingData.webview.webViewClient = InnerWebViewClient(onObtainCode)
        bindingData.webview.settings.javaScriptEnabled = true
        bindingData.webview.loadUrl(authUri.toString())

        viewModel.accessToken.observe(viewLifecycleOwner, Observer {
            if (it != "") {
                Log.d("デバッグ", it)
            }
        })
    }

    private class InnerWebViewClient(
        val onObtainCode: (code: String) -> Unit
    ) : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            view ?: return

            val code = Uri.parse(view.url).getQueryParameter("code")
            code ?: return

            onObtainCode(code)
        }
    }
}