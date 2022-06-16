package com.br3ant.wanandroidcompose.repo

import com.br3ant.wanandroidcompose.ui.entity.ArticleListData
import com.br3ant.wanandroidcompose.ui.entity.HomeBannerData
import com.br3ant.wanandroidcompose.utils.http.PageList
import rxhttp.RxHttp
import rxhttp.toFlowResponse
import rxhttp.toResponse


object HomeRepo {

    //首页列表
    suspend fun getHomeList(page: Int) =
        RxHttp.get("/article/list/$page/json").toResponse<PageList<ArticleListData>>().await()

    //首页轮播图
    fun getBanner() = RxHttp.get("/banner/json").toFlowResponse<List<HomeBannerData>>()

    //获取置顶文章列表
    fun getArticleTopList() = ""

}