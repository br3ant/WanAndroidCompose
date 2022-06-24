package com.br3ant.wanandroidcompose.ui.page.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.core.view.WindowCompat


class WebActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val targetUrl = requireNotNull(intent.getStringExtra(TARGET_URL))
        setContent {
            WebRoute(targetUrl) {
                finish()
            }
        }
    }

    companion object {
        private const val TARGET_URL = "TARGET_URL"

        fun open(context: Context, targetUrl: String) {
            context.startActivity(Intent(context, WebActivity::class.java).apply {
                putExtra(TARGET_URL, targetUrl)
            })
        }
    }
}
