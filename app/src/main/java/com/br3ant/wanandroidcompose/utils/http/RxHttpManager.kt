package com.br3ant.wanandroidcompose.utils.http

import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.annotation.DefaultDomain
import rxhttp.wrapper.param.Param
import java.util.concurrent.TimeUnit


/**
 * @author houqiqi on 2022/6/15
 */
object RxHttpManager {

    @DefaultDomain
    @JvmField
    val baseUrl = "https://www.wanandroid.com/"

    fun init() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

        RxHttpPlugins.init(client)
            .setOnParamAssembly { p: Param<*>? ->
                p
            }
    }
}