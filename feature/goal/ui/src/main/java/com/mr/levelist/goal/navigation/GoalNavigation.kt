package com.mr.levelist.goal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mr.levelist.core.ui.navigation.model.ScreenConfig
import com.mr.levelist.core.ui.navigation.addDrawerMenuIconIfPossible
import com.mr.levelist.goal.ui.GoalScreen

fun NavGraphBuilder.addGoalNavigation(
    navController: NavController,
    updateScreenConfig: (ScreenConfig) -> Unit,
    openDrawer: () -> Unit
) {
    composable(GoalRoute.Goals.route) {
        GoalScreen(
            setScreenConfig = { screenConfig ->
                val goalsConfig = if (screenConfig.isDrawerMenuIconEnabled) {
                    screenConfig.addDrawerMenuIconIfPossible(openDrawer)
                } else {
                    screenConfig
                }
                updateScreenConfig(goalsConfig)
            },
        )
    }
}