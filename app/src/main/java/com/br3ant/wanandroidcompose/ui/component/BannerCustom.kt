package com.br3ant.wanandroidcompose.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.changedToDownIgnoreConsumed
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.br3ant.wanandroidcompose.R
import com.br3ant.wanandroidcompose.ui.entity.HomeBannerData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

/**
 * ?????????
 * [timeMillis] ????????????
 * [loadImage] ????????????????????????
 * [indicatorAlignment] ?????????????????????,?????????????????????????????????,?????????padding
 * [onClick] ?????????????????????
 */
@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun Banner(
    list: List<HomeBannerData>?,
    timeMillis: Long = 3000,
    @DrawableRes loadImage: Int = R.mipmap.ic_web,
    indicatorAlignment: Alignment = Alignment.BottomCenter,
    onClick: (link: String) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .height(220.dp)
    ) {

        if (list == null) {
            //??????????????????
            Image(
                painterResource(loadImage),
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        } else {
            val pagerState = rememberPagerState(
                //?????????
                pageCount = list.size,
                //??????????????????
                initialOffscreenLimit = 1,
                //??????????????????
                infiniteLoop = true,
                //????????????
                initialPage = 0
            )

            //??????????????????
            var executeChangePage by remember { mutableStateOf(false) }
            var currentPageIndex = 0

            //????????????
            LaunchedEffect(pagerState.currentPage, executeChangePage) {
                if (pagerState.pageCount > 0) {
                    delay(timeMillis)
                    //????????????+1???????????????????????????infiniteLoop == true
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .pointerInput(pagerState.currentPage) {
                        awaitPointerEventScope {
                            while (true) {
                                //PointerEventPass.Initial - ?????????????????????????????????????????????????????????
                                val event = awaitPointerEvent(PointerEventPass.Initial)
                                //?????????????????????????????????
                                val dragEvent = event.changes.firstOrNull()
                                when {
                                    //????????????????????????????????????
                                    dragEvent!!.isConsumed -> {
                                        return@awaitPointerEventScope
                                    }
                                    //??????????????????(?????????????????????????????????)
                                    dragEvent.changedToDownIgnoreConsumed() -> {
                                        //?????????????????????????????????
                                        currentPageIndex = pagerState.currentPage
                                    }
                                    //??????????????????(?????????????????????????????????)
                                    dragEvent.changedToUpIgnoreConsumed() -> {
                                        //???????????????????????????/???????????????pagerState.targetPage???null??????????????????????????????
                                        if (pagerState.targetPage == null) return@awaitPointerEventScope
                                        //???pageCount??????1?????????????????????????????????????????????????????????????????????
                                        if (currentPageIndex == pagerState.currentPage && pagerState.pageCount > 1) {
                                            executeChangePage = !executeChangePage
                                        }
                                    }
                                }
                            }
                        }
                    }
                    .clickable(onClick = { onClick(list[pagerState.currentPage].url ?: "") })
                    .fillMaxSize(),
            ) { page ->
                Image(
                    painter = rememberAsyncImagePainter(list[page].imagePath),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier
                    .align(indicatorAlignment)
                    .padding(bottom = 6.dp, start = 6.dp, end = 6.dp)
            ) {

                //?????????
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in list.indices) {
                        //??????
                        var size by remember { mutableStateOf(5.dp) }
                        size = if (pagerState.currentPage == i) 7.dp else 5.dp

                        //??????
                        val color =
                            if (pagerState.currentPage == i) MaterialTheme.colorScheme.primary else Color.Gray

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(color)
                                //???size???????????????????????????????????????
                                .animateContentSize()
                                .size(size)
                        )
                        //?????????????????????
                        if (i != list.lastIndex) Spacer(
                            modifier = Modifier
                                .height(0.dp)
                                .width(4.dp)
                        )
                    }
                }

            }
        }
    }
}