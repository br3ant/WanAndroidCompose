package com.br3ant.wanandroidcompose.ui.page.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br3ant.wanandroidcompose.repo.BookRepo
import com.br3ant.wanandroidcompose.ui.entity.Book
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/**
 * @author houqiqi on 2022/6/23
 */
class BookViewModel : ViewModel() {

    val bookListData: StateFlow<List<Book>> =
        BookRepo.getBookList().stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

}