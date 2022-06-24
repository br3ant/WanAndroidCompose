package com.br3ant.wanandroidcompose.ui.page.home

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.br3ant.wanandroidcompose.ui.navigation.WanNavigationDestination

/**
 * @author houqiqi on 2022/6/13
 */
object HomeDestination : WanNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homeGraph() {
    composable(route = HomeDestination.route) {
        HomeRoute()
    }
}