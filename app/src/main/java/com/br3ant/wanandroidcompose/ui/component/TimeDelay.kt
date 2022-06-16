package com.br3ant.wanandroidcompose.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

/**
 * @author houqiqi on 2022/6/16
 */
@Composable
fun TimeDelay(timeMillis: Long, block: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        delay(timeMillis)
        block()
    }
}
