package com.br3ant.wanandroidcompose.ui.page.book

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.br3ant.wanandroidcompose.ui.navigation.WanNavigationDestination

/**
 * @author houqiqi on 2022/6/13
 */
object BookDestination : WanNavigationDestination {
    override val route = "book_route"
    override val destination = "book_destination"
}

fun NavGraphBuilder.bookGraph(
    navigateToChapter: (String,String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {

    navigation(
        route = BookDestination.route,
        startDestination = BookDestination.destination
    ) {
        composable(route = BookDestination.destination) {
            BookRoute(
                navigateToChapter = navigateToChapter,
            )
        }
        nestedGraphs()
    }
}