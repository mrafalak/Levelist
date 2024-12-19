package com.mr.levelist.navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
object HomeGraph

@Serializable
private object HomeRoot

fun NavGraphBuilder.homeNavigation(rootNavController: NavHostController) {
    navigation<HomeGraph>(startDestination = HomeRoot) {
        composable<HomeRoot> {
            val nestedNavController = rememberNavController()

            HomeNavHost(
                rootNavController = rootNavController,
                nestedNavController = nestedNavController,
            )
        }
    }
}