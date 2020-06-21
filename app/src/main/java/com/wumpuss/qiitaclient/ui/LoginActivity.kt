package com.wumpuss.qiitaclient.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), LoginFragment.Callback {
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (savedInstanceState == null) {
            val fragment = LoginFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, LoginFragment.TAG)
                .commit()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent ?: return

        val code = intent.data?.getQueryParameter("code") ?: return
        val loginFragment = supportFragmentManager.findFragmentByTag(LoginFragment.
        TAG)
        if (loginFragment is LoginFragment) {
            loginFragment.requestAccessToken(code)
        }
    }

    override fun onAuthCompleted() {
        Toast.makeText(this, "ログインが完了しました", Toast.LENGTH_SHORT).show()
        intent.putExtra("INPUT_ACCESS_TOKEN", viewModel.accessToken.value)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}