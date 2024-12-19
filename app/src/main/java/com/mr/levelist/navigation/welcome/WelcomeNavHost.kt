package com.mr.levelist.navigation.welcome

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mr.levelist.core.ui.navigation.AppRoute
import com.mr.levelist.feature.welcome.ui.LoginScreen
import com.mr.levelist.feature.welcome.ui.WelcomeScreen
import com.mr.levelist.navigation.home.HomeGraph
import kotlinx.serialization.Serializable

private sealed interface WelcomeRoute : AppRoute {
    @Serializable
    data object Welcome : WelcomeRoute {
        override val route: String = "welcome"
    }

    @Serializable
    data object Login : WelcomeRoute {
        override val route: String = "login"
    }
}

@Composable
fun WelcomeNavHost(
    rootNavController: NavHostController,
    nestedNavController: NavHostController,
) {

    Scaffold { innerPadding ->
        NavHost(
            navController = nestedNavController,
            startDestination = WelcomeRoute.Welcome.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(WelcomeRoute.Welcome.route) {
                WelcomeScreen(
                    navigateToLogIn = {
                        nestedNavController.navigate(WelcomeRoute.Login.route)
                    }
                )
            }
            composable(WelcomeRoute.Login.route) {
                LoginScreen(
                    navigateToHome = {
                        rootNavController.navigate(HomeGraph) {
                            popUpTo(WelcomeGraph) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
