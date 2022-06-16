package com.br3ant.wanandroidcompose.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefreshState

/**
 * 统一管理Paging数据状态的方法
 * 错误处理、加载中的显示方式
 */
@Composable
fun <T : Any> PagingState(
    //paging数据
    pagingData: LazyPagingItems<T>,
    //刷新状态
    refreshState: SwipeRefreshState,
    content: @Composable () -> Unit
) {

    when (val state = pagingData.loadState.refresh) {
        //未加载且未观察到错误
        is LoadState.NotLoading -> NotLoading(refreshState) {
            //允许显示无数据布局 这里通常是第一次获取数据
            when (pagingData.itemCount) {
                0 -> Empty(pagingData = pagingData)
                else -> content()
            }
        }
        //加载失败
        is LoadState.Error -> Error(pagingData = pagingData, state.error)
        //加载中
        LoadState.Loading -> Loading()
    }

    //如果在加载途中遇到错误的话，pagingData的状态为append
    when (pagingData.loadState.append) {
        //加载失败
        is LoadState.Error -> Error(pagingData)
        //加载中
        LoadState.Loading -> Loading()
    }

}

/**
 * 未加载且未观察到错误
 */
@Composable
private fun NotLoading(
    refreshState: SwipeRefreshState,
    content: @Composable () -> Unit
) {

    content()

    //让刷新头停留一下子再收回去
    TimeDelay(2000) {
        refreshState.isRefreshing = false
    }
}

/**
 * 加载失败
 */
@Composable
private fun <T : Any> Error(
    pagingData: LazyPagingItems<T>,
    error: Throwable? = null
) {
    error?.message?.let {
        ErrorComposable(it) {
            pagingData.refresh()
        }
    } ?: run {
        ErrorComposable {
            pagingData.refresh()
        }
    }
}

@Composable
private fun <T : Any> Empty(pagingData: LazyPagingItems<T>) {
    ErrorComposable("暂无数据，请点击重试") {
        pagingData.refresh()
    }
}

@Composable
private fun Loading() {
    Row(modifier = Modifier.fillMaxSize()) {

    }
}





