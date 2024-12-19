package com.mr.levelist.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mr.levelist.core.ui.navigation.model.ScreenConfig
import com.mr.levelist.settings.ui.SettingsScreen

fun NavGraphBuilder.addSettingsNavigation(
    navController: NavController,
    updateScreenConfig: (ScreenConfig) -> Unit,
) {
    composable(SettingsRoute.Settings.route) {
        SettingsScreen(
            setScreenConfig = { screenConfig ->
                updateScreenConfig(screenConfig)
            }
        )
    }
}