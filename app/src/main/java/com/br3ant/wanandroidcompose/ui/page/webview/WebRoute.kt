package com.br3ant.wanandroidcompose.ui.page.webview

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br3ant.wanandroidcompose.R
import com.br3ant.wanandroidcompose.ui.component.WanGradientBackground
import com.br3ant.wanandroidcompose.ui.component.WanTopAppBar
import com.br3ant.wanandroidcompose.ui.icons.WanIcons
import com.br3ant.wanandroidcompose.ui.theme.WanTheme

/**
 * @author houqiqi on 2022/6/24
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun WebRoute(
    targetUrl: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    WanTheme {
        WanGradientBackground {
            Scaffold(
//                topBar = {
//                    WanTopAppBar(
//                        titleRes = R.string.article_app_bar_title,
//                        navigationIcon = {
//                            Icon(
//                                imageVector = WanIcons.ArrowBack,
//                                contentDescription = null
//                            )
//                        },
//                        actionIcon = {
//                            Icon(
//                                imageVector = WanIcons.AccountCircle,
//                                contentDescription = null
//                            )
//                        },
//                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                            containerColor = Color.Transparent
//                        ),
//                        modifier = Modifier.windowInsetsPadding(
//                            WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
//                        ),
//                        onNavigationClick = onBackClick
//                    )
//                },
                containerColor = Color.Transparent
            ) { innerPadding ->
                BoxWithConstraints(
                    modifier = modifier
                        .padding(innerPadding)
                        .consumedWindowInsets(innerPadding)
                ) {
                    WebScreen(targetUrl = targetUrl)
                }
            }
        }
    }

}

@Composable
private fun WebScreen(
    targetUrl: String
) {
    AndroidView(factory = {
        val context = it
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.apply {
                javaScriptEnabled = true
            }
            loadUrl(targetUrl)
            Log.i("WebScreen", "加载url=$targetUrl")
        }

    }, modifier = Modifier.fillMaxSize(),
        update = { it.loadUrl(targetUrl) })
}