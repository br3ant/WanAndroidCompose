package com.br3ant.wanandroidcompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
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
    state: LazyListState,
    header: LazyListScope.() -> Unit = {},
    content: @Composable (data: T) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        val refreshState =
            rememberSwipeRefreshState(lazyPagingListData.loadState.refresh is LoadState.Loading)

        SwipeRefresh(
            state = refreshState,
            onRefresh = {
                //刷新数据
                lazyPagingListData.refresh()
            }
        ) {
            //列表数据
            LazyColumn(
                modifier = Modifier.fillMaxSize(), state = state
            ) {
                header()
                itemsIndexed(lazyPagingListData) { _, data ->
                    content(data!!)
                }

                //bottom 加载更多
                if (lazyPagingListData.loadState.append is LoadState.Loading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
        if (lazyPagingListData.loadState.refresh is LoadState.Loading) {
            if (lazyPagingListData.itemCount == 0) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        } else if (lazyPagingListData.loadState.refresh is LoadState.Error) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Button(
                    modifier = Modifier,
                    onClick = { lazyPagingListData.refresh() }) {
                    Text(text = "加载失败，请重试！")
                }
            }
        }

    }
}