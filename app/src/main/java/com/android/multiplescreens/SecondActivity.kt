package com.android.multiplescreens

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // load app 2
        val webview = findViewById<WebView>(R.id.webView2)
        webview.settings.javaScriptEnabled = true
        webview.loadUrl("file:///android_asset/app2.html")
    }
}
