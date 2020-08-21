package com.example.lmsstikes.view.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lmsstikes.view.main.MainActivity
import com.example.lmsstikes.R
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.view.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        findViewById<View>(android.R.id.content).systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setSplashScreen()
    }

    private fun setSplashScreen(){
        Handler().postDelayed({
            when {
                AppPreference.isFirstTime() -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                AppPreference.isLogin() -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                else -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
            finish()
        },5000)
    }
}