package com.br3ant.wanandroidcompose.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @author houqiqi on 2022/6/24
 */

@Composable
fun TransparentStatusBar() {
    val uiController = rememberSystemUiController()
    uiController.setStatusBarColor(
        Color.Transparent, MaterialTheme.colors.isLight
    )
}