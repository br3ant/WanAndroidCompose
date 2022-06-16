package com.br3ant.wanandroidcompose.ui.page.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.br3ant.wanandroidcompose.repo.HomeRepo
import com.br3ant.wanandroidcompose.ui.entity.ArticleListData
import com.br3ant.wanandroidcompose.ui.entity.HomeBannerData
import com.br3ant.wanandroidcompose.utils.paging.CommonPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/**
 * @author houqiqi on 2022/6/13
 */
class HomeViewModel : ViewModel() {

    val bannerListData: StateFlow<List<HomeBannerData>> =
        HomeRepo.getBanner().stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    //首页列表
    val homeListData: Flow<PagingData<ArticleListData>> = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage: Int ->
            HomeRepo.getHomeList(nextPage)
        }
    }.flow.cachedIn(viewModelScope)
}