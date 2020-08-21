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
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.view.base.BaseActivity
import com.example.lmsstikes.view.main.MainActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onClickLogin(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
//    private lateinit var binding: ActivityLoginBinding
//    private val viewModel by inject<LoginViewModel>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        findViewById<View>(android.R.id.content).systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
//        binding.vm = viewModel
//        binding.lifecycleOwner = this
//
//        with(viewModel) {
//            hideKeyBoard.observe(this@LoginActivity, Observer {
//                UtilityHelper.hideSoftKeyboard(this@LoginActivity)
//            })
//            snackbarMessage.observe(this@LoginActivity, Observer {
//                when (it) {
//                    is Int -> UtilityHelper.snackbarLong(view_parent, getString(it))
//                    is String -> UtilityHelper.snackbarLong(view_parent, it)
//                }
//            })
//            networkError.observe(this@LoginActivity, Observer {
//                UtilityHelper.snackbarLong(view_parent, getString(R.string.error_network))
//            })
//            isLoading.observe(this@LoginActivity, Observer { bool ->
//                bool.let { loading ->
//                    if(loading){ showWaitingDialog() }
//                    else { hideWaitingDialog() }
//                }
//            })
//            loginAccept.observe(this@LoginActivity, Observer {
//                UserHelper.signInWithFirebaseAuth(this@LoginActivity, AppPreference.getLoginData().email)
//            })
//            openLoginWithPhone.observe(this@LoginActivity, Observer {
//                this@LoginActivity.finish()
//            })
//            openSignUp.observe(this@LoginActivity, Observer {
//                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java ))
//            })
//            emailError.observe(this@LoginActivity, Observer { code ->
//                when (code) {
//                    0 -> txtEmailError.text = getString(R.string.error_empty_form)
//                    1 -> txtEmailError.text = getString(R.string.error_email_invalid)
//                }
//            })
//        }
//
//        AppPreference.putFirstTime(false)
//        requestLoginPermission()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        setToolbar(getString(R.string.login))
//    }
//
//    private fun requestLoginPermission() {
//        PermissionHelper.setPermissionListener(object : PermissionHelper.Companion.PermissionListener{
//            override fun onPermissionGranted(isAllGranted: Boolean) {
//                if (isAllGranted) {
//                    //Do Nothing
//                } else {
//                    Toast.makeText(
//                        this@LoginActivity, "Phone dan SMS " +
//                                "permission dibutuhkan!", Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        })
//        PermissionHelper.requestPermission(this, listOf(
//            Manifest.permission.READ_PHONE_STATE)
//        )
//    }

