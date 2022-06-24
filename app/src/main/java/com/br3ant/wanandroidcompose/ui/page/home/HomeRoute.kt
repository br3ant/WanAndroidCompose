package com.br3ant.wanandroidcompose.ui.page.home

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.br3ant.wanandroidcompose.ui.component.Banner
import com.br3ant.wanandroidcompose.ui.component.HomeCardItemContent
import com.br3ant.wanandroidcompose.ui.component.WanGradientBackground
import com.br3ant.wanandroidcompose.ui.component.SwipeRefreshContent
import com.br3ant.wanandroidcompose.ui.page.webview.WebActivity
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * @author houqiqi on 2022/6/13
 */
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class, ExperimentalPagerApi::class,
    ExperimentalCoilApi::class
)
@Composable
fun HomeRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    val homeViewModel: HomeViewModel = viewModel()
    val bannerListData by homeViewModel.bannerListData.collectAsState()
    val homeListData = homeViewModel.homeListData.collectAsLazyPagingItems()
    WanGradientBackground {
        Scaffold(containerColor = Color.Transparent) { innerPadding ->
            BoxWithConstraints(
                modifier = modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding)
            ) {
                //首页页面的内容
                SwipeRefreshContent(
                    homeListData,
                    header = {
                        item {
                            //轮播图
                            Banner(bannerListData)
                        }

                        //置顶数据

                    }) { _, data ->
                    val context = LocalContext.current
                    HomeCardItemContent(article = data) {
                        WebActivity.open(
                            context,
                            data.link ?: ""
                        )
                    }
                }
            }
        }
    }
}