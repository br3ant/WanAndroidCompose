package com.br3ant.wanandroidcompose.ui.page.home

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.br3ant.wanandroidcompose.ui.component.NiaGradientBackground

/**
 * @author houqiqi on 2022/6/13
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    NiaGradientBackground {
        Scaffold() { innerPadding ->
            BoxWithConstraints(
                modifier = modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding)
            ) {}
        }
    }
}