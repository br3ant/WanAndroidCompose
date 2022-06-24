package com.br3ant.wanandroidcompose.ui.page.book

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br3ant.wanandroidcompose.R
import com.br3ant.wanandroidcompose.ui.component.BookItemContent
import com.br3ant.wanandroidcompose.ui.component.WanTopAppBar
import com.br3ant.wanandroidcompose.ui.component.SimpleCard
import com.br3ant.wanandroidcompose.ui.component.WanGradientBackground
import com.br3ant.wanandroidcompose.ui.icons.WanIcons

/**
 * @author houqiqi on 2022/6/13
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BookRoute(
    navigateToChapter: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bookViewModel: BookViewModel = viewModel()

    val chapterListData by bookViewModel.bookListData.collectAsState()
    WanGradientBackground {
        Scaffold(
            topBar = {
                WanTopAppBar(
                    titleRes = R.string.book_app_bar_title,
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = WanIcons.Bookmarks),
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
                    )
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            BoxWithConstraints(
                modifier = modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding)
            ) {
                LazyColumn {
                    items(chapterListData.size) { index ->
                        SimpleCard {
                            BookItemContent(book = chapterListData[index]) {
                                navigateToChapter(
                                    chapterListData[index].id,
                                    chapterListData[index].name
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}