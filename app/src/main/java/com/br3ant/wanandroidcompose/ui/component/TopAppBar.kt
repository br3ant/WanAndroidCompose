package com.br3ant.wanandroidcompose.ui.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WanTopAppBar(
    @StringRes titleRes: Int = 0,
    titleString: String = "",
    navigationIcon: @Composable () -> Unit,
    actionIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(text = if (titleRes != 0) stringResource(id = titleRes) else titleString) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                navigationIcon()
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                actionIcon()
            }
        },
        colors = colors,
        modifier = modifier
    )
}
