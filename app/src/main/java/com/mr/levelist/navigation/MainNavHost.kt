package com.mr.levelist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mr.levelist.navigation.home.HomeGraph
import com.mr.levelist.navigation.home.homeNavigation
import com.mr.levelist.navigation.welcome.WelcomeGraph
import com.mr.levelist.navigation.welcome.welcomeNavigation

@Composable
fun MainNavHost(isUserLoggedIn: Boolean) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn) HomeGraph else WelcomeGraph
    ) {
        welcomeNavigation(navController)
        homeNavigation(navController)
    }
}