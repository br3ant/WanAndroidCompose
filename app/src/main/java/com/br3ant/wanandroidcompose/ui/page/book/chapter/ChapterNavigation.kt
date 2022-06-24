package com.br3ant.wanandroidcompose.ui.page.book.chapter

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.br3ant.wanandroidcompose.ui.navigation.WanNavigationDestination

object ChapterDestination : WanNavigationDestination {
    override val route = "chapter_route"
    override val destination = "chapter_destination"
    const val bookIdArg = "bookId"
    const val bookNameArg = "bookName"
}

fun NavGraphBuilder.chapterGraph(
    onBackClick: () -> Unit
) {
    composable(
        route = "${ChapterDestination.route}/{${ChapterDestination.bookIdArg}}/{${ChapterDestination.bookNameArg}}",
        arguments = listOf(
            navArgument(ChapterDestination.bookIdArg) {
                type = NavType.StringType
            },
            navArgument(ChapterDestination.bookNameArg) {
                type = NavType.StringType
            }
        )
    ) {
        ChapterRoute(onBackClick = onBackClick)
    }
}
