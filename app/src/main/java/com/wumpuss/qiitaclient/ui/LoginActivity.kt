package com.wumpuss.qiitaclient.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
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

    override fun onAuthCompleted() {
        Toast.makeText(this, "ログインが完了しました", Toast.LENGTH_SHORT).show()
        intent.putExtra("INPUT_ACCESS_TOKEN", viewModel.accessToken.toString())
        setResult(Activity.RESULT_OK)
        finish()
    }
}