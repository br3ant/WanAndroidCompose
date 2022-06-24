package com.br3ant.wanandroidcompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.br3ant.wanandroidcompose.ui.entity.Book

/**
 * @author houqiqi on 2022/6/23
 */

@Composable
fun BookItemContent(
    book: Book,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(book.cover),
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(),

            contentDescription = book.author
        )

        Column(
            modifier = Modifier.weight(1f, true),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                book.name,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                color = Color.DarkGray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "作者:${book.author}",
                modifier = Modifier,
                color = Color.Red,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                book.desc,
                modifier = Modifier,
                maxLines = 2,
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }


}