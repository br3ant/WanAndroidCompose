package com.br3ant.wanandroidcompose.ui.page.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br3ant.wanandroidcompose.ui.entity.BannerData

/**
 * @author houqiqi on 2022/6/13
 */
class HomeViewModel : ViewModel() {

    val bannerListData: MutableLiveData<List<BannerData>> = MutableLiveData<List<BannerData>>()
}