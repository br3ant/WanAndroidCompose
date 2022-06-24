package com.br3ant.wanandroidcompose.utils.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.StandardCharsets

class LogInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().also { logRequest(it) }
        return chain.proceed(request).also {
            logResponse(it)
        }
    }

    private fun logRequest(request: Request) =
        Log.i(TAG, "http -- start -- ${formatRequest(request)}")


    private fun formatRequest(request: Request): String {
        return try {
            val requestBody = request.body
            if (requestBody != null) {
                val buffer = Buffer()
                requestBody.writeTo(buffer)
                val charset = requestBody.contentType()?.charset(UTF8)
                val body = buffer.readString(charset ?: UTF8)
                "method = ${request.method} -- url = ${request.url}?${body}&token=${request.header("token") ?: ""}"
            } else {
                "method = ${request.method} -- url = ${request.url}&token=${request.header("token") ?: ""}"
            }
        } catch (e: Exception) {
            "request print error = ${e.message}"
        }
    }

    private fun logResponse(response: Response?) {
        val body = response?.body ?: return
        Log.i(TAG, "${formatRequest(response.request)}/n${ResponseUtils.okToString(body)}")
    }

    companion object {
        private val UTF8 = StandardCharsets.UTF_8
        private const val TAG = "OkHttp"

    }
}