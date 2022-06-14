package com.br3ant.wanandroidcompose.ui.page.tutorial

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.br3ant.wanandroidcompose.ui.navigation.WanNavigationDestination

/**
 * @author houqiqi on 2022/6/13
 */
object TutorialDestination : WanNavigationDestination {
    override val route = "tutorial_route"
    override val destination = "tutorial_destination"
}

fun NavGraphBuilder.tutorialGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = TutorialDestination.route) {
        TutorialRoute(windowSizeClass)
    }
}