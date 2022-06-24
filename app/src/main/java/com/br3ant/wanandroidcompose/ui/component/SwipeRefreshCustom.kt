package com.br3ant.wanandroidcompose.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * 带刷新头的Card布局
 * LazyPagingItems<T>
 */
@Composable
fun <T : Any> SwipeRefreshContent(
    lazyPagingListData: LazyPagingItems<T>,
    cardHeight: Dp = 120.dp,
    state: LazyListState = rememberLazyListState(),
    header: LazyListScope.() -> Unit = {},
    content: @Composable (index: Int, data: T) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        val refreshState = rememberSwipeRefreshState(false)

        SwipeRefresh(
            state = refreshState,
            onRefresh = {
                //刷新数据
                lazyPagingListData.refresh()
            }
        ) {
            //列表数据
            PagingState(lazyPagingListData, refreshState) {
                LazyColumn(modifier = Modifier.fillMaxSize(), state = state) {
                    header()
                    itemsIndexed(lazyPagingListData) { index, data ->
                        SimpleCard(cardHeight = cardHeight) {
                            content(index, data!!)
                        }
                    }
                }
            }
        }

    }
}

/**
 * 带刷新头的Card布局
 * LazyPagingItems<T>
 * Card高度自适应
 */
@Composable
fun <T : Any> SwipeRefreshContent(
    viewModel: ViewModel,
    listData: List<T>?,
    state: LazyListState = rememberLazyListState(),
    noData: () -> Unit,
    content: @Composable (data: T) -> Unit
) {

    if (listData == null) return

    if (listData.isEmpty()) {
        ErrorComposable("暂无数据，请点击重试") {
            noData()
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {

        val refreshState = rememberSwipeRefreshState(false)

        SwipeRefresh(
            state = refreshState,
            onRefresh = {
                //显示刷新头
                refreshState.isRefreshing = true
                //刷新数据
                noData()
//                viewModel.sleepTime(3000) {
//                    refreshState.isRefreshing = false
//                }
            }
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize(), state = state) {
                itemsIndexed(listData) { index, data ->
                    SimpleCard {
                        content(data)
                    }
                }
            }
        }

    }
}