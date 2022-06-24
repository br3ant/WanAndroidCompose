package com.br3ant.wanandroidcompose.utils.http

import okhttp3.ResponseBody
import okio.Buffer
import java.io.EOFException
import java.nio.charset.StandardCharsets


object ResponseUtils {
    private val UTF8 = StandardCharsets.UTF_8

    fun okToString(body: ResponseBody): String? {
        try {
            val source = body.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer
            if (!isPlaintext(buffer)) {
                return null
            }
            if (body.contentLength() != 0L) {
                return buffer.clone().readString(body.contentType()?.charset(UTF8) ?: UTF8)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    private fun isPlaintext(buffer: Buffer): Boolean {
        return try {
            val prefix = Buffer()
            val byteCount = if (buffer.size < 64) buffer.size else 64
            buffer.copyTo(prefix, 0, byteCount)
            for (i in 0..15) {
                if (prefix.exhausted()) {
                    break
                }
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
            }
            true
        } catch (e: EOFException) {
            false
        }
    }
}