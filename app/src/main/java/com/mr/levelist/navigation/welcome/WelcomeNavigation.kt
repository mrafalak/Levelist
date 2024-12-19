package com.mr.levelist.navigation.welcome

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
object WelcomeGraph

@Serializable
private object WelcomeRoot

fun NavGraphBuilder.welcomeNavigation(rootNavController: NavHostController) {
    navigation<WelcomeGraph>(startDestination = WelcomeRoot) {
        composable<WelcomeRoot> {
            val nestedNavController = rememberNavController()

            WelcomeNavHost(
                rootNavController = rootNavController,
                nestedNavController = nestedNavController,
            )
        }
    }
}