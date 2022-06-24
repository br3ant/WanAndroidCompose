package com.br3ant.wanandroidcompose.ui.page.book.chapter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.br3ant.wanandroidcompose.repo.BookRepo
import com.br3ant.wanandroidcompose.ui.entity.ArticleListData
import com.br3ant.wanandroidcompose.utils.paging.CommonPagingSource
import kotlinx.coroutines.flow.Flow

/**
 * @author houqiqi on 2022/6/23
 */
class ChapterViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val bookId: String = checkNotNull(savedStateHandle[ChapterDestination.bookIdArg])

    val bookName: String = checkNotNull(savedStateHandle[ChapterDestination.bookNameArg])

    val chapterListData: Flow<PagingData<ArticleListData>> = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage: Int ->
            BookRepo.getBookChapterList(bookId, nextPage)
        }
    }.flow.cachedIn(viewModelScope)
}