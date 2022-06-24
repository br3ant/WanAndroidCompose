package com.br3ant.wanandroidcompose.ui.page.book.chapter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.br3ant.wanandroidcompose.R
import com.br3ant.wanandroidcompose.ui.component.Banner
import com.br3ant.wanandroidcompose.ui.component.BookItemContent
import com.br3ant.wanandroidcompose.ui.component.HomeCardItemContent
import com.br3ant.wanandroidcompose.ui.component.SwipeRefreshContent
import com.br3ant.wanandroidcompose.ui.component.WanGradientBackground
import com.br3ant.wanandroidcompose.ui.component.WanTopAppBar
import com.br3ant.wanandroidcompose.ui.icons.WanIcons
import com.br3ant.wanandroidcompose.ui.page.book.BookViewModel
import com.br3ant.wanandroidcompose.ui.page.webview.WebActivity

/**
 * @author houqiqi on 2022/6/23
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ChapterRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val chapterViewModel: ChapterViewModel = viewModel()

    val chapterListData = chapterViewModel.chapterListData.collectAsLazyPagingItems()
    WanGradientBackground {
        Scaffold(
            topBar = {
                WanTopAppBar(
                    titleString = chapterViewModel.bookName,
                    navigationIcon = {
                        Icon(
                            imageVector = WanIcons.ArrowBack,
                            contentDescription = null
                        )
                    },
                    actionIcon = {
                        Icon(
                            imageVector = WanIcons.AccountCircle,
                            contentDescription = null
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                    ),
                    onNavigationClick = onBackClick
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            BoxWithConstraints(
                modifier = modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding)
            ) {
                SwipeRefreshContent(
                    chapterListData,
                    cardHeight = 30.dp
                ) { _, data ->
                    val context = LocalContext.current
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart)
                        .clickable {
                            WebActivity.open(
                                context,
                                data.link ?: ""
                            )
                        }) {
                        Text(
                            data.title ?: "",
                            modifier = Modifier.padding(4.dp),
                            maxLines = 1,
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}