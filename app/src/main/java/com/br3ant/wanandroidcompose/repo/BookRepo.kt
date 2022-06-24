package com.br3ant.wanandroidcompose.repo

import com.br3ant.wanandroidcompose.ui.entity.ArticleListData
import com.br3ant.wanandroidcompose.ui.entity.Book
import com.br3ant.wanandroidcompose.utils.http.PageList
import rxhttp.RxHttp
import rxhttp.toFlowResponse
import rxhttp.toResponse

/**
 * @author houqiqi on 2022/6/23
 */
object BookRepo {

    fun getBookList() =
        RxHttp.get("/chapter/547/sublist/json").toFlowResponse<List<Book>>()

    //指定教程下的章节目录 和文章列表返回结构体一致
    suspend fun getBookChapterList(bookId: String, page: Int) =
        RxHttp.get("/article/list/$page/json?cid=$bookId&order_type=1")
            .toResponse<PageList<ArticleListData>>().await()

}