package com.example.lmsstikes.view.login

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.lmsstikes.R
import com.example.lmsstikes.databinding.ActivityLoginBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.view.base.BaseActivity
import com.example.lmsstikes.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by inject<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<View>(android.R.id.content).systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        with(viewModel) {
            hideKeyBoard.observe(this@LoginActivity, Observer {
                UtilityHelper.hideSoftKeyboard(this@LoginActivity)
            })
            snackbarMessage.observe(this@LoginActivity, Observer {
                when (it) {
                    is Int -> UtilityHelper.snackbarLong(view_parent, getString(it))
                    is String -> UtilityHelper.snackbarLong(view_parent, it)
                }
            })
            networkError.observe(this@LoginActivity, Observer {
                UtilityHelper.snackbarLong(view_parent, getString(R.string.error_network))
            })
            isLoading.observe(this@LoginActivity, Observer { bool ->
                bool.let { loading ->
                    if(loading){ showWaitingDialog() }
                    else { hideWaitingDialog() }
                }
            })
            loginSuccess.observe(this@LoginActivity, Observer {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            })
        }

//        AppPreference.putFirstTime(false)
        viewModel.username.value = "mhs123"
        viewModel.password.value = "123"
    }
}

