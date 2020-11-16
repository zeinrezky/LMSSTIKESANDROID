package com.example.lmsstikes.view.main

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import com.example.lmsstikes.R
import com.example.lmsstikes.util.Constant
import com.example.lmsstikes.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<View>(android.R.id.content).systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        setContentView(R.layout.activity_web_view)
        setView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setView() {
        setToolbar(getString(R.string.loading))
        web_view.apply {
            settings.javaScriptEnabled = true
            Log.d("url", intent.getStringExtra(Constant.Header.URL)!!)
            loadUrl(intent.getStringExtra(Constant.Header.URL))
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    Log.d("web_onPageFinished", url)
                    setToolbar(view.title)
                    loading.visibility = View.GONE
                }
                override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                    Log.d("web_onReceivedError", error.toString())
                    loading.visibility = View.GONE
                }
                override fun onReceivedHttpError(view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse) {
                    Log.d("web_onReceivedHttpError", errorResponse.toString())
                    loading.visibility = View.GONE
                }
                override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                    Log.d("web_onReceivedSslError", error.toString())
                    loading.visibility = View.GONE
                }
            }
        }
    }
}