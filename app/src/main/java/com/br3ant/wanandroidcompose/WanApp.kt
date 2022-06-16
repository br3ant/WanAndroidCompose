package com.br3ant.wanandroidcompose

import android.app.Application
import com.br3ant.wanandroidcompose.utils.http.RxHttpManager

/**
 * @author houqiqi on 2022/6/15
 */
class WanApp : Application() {

    override fun onCreate() {
        super.onCreate()
        RxHttpManager.init()
    }
}