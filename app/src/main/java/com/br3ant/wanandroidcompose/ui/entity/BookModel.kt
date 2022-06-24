package com.br3ant.wanandroidcompose.ui.entity

/**
 * @author houqiqi on 2022/6/23
 */
data class Book(
    val author: String,
    val children: List<Any>,
    val courseId: Int,
    val cover: String,
    val desc: String,
    val id: String,
    val lisense: String,
    val lisenseLink: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)