package com.mr.levelist.habit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mr.levelist.core.ui.navigation.model.ScreenConfig
import com.mr.levelist.core.ui.navigation.addDrawerMenuIconIfPossible
import com.mr.levelist.habit.ui.CreateHabitScreen
import com.mr.levelist.habit.ui.HabitListScreen

fun NavGraphBuilder.addHabitNavigation(
    navController: NavController,
    updateScreenConfig: (ScreenConfig) -> Unit,
    openDrawer: () -> Unit
) {
    composable(HabitRoute.HabitList.route) {
        HabitListScreen(
            setScreenConfig = { screenConfig ->
                val habitListConfig = if (screenConfig.isDrawerMenuIconEnabled) {
                    screenConfig.addDrawerMenuIconIfPossible(openDrawer)
                } else {
                    screenConfig
                }
                updateScreenConfig(habitListConfig)
            },
            navigateToCreateHabit = {
                navController.navigate(HabitRoute.CreateHabit.route)
            }
        )
    }
    composable(HabitRoute.CreateHabit.route) {
        CreateHabitScreen(
            setScreenConfig = { screenConfig ->
                updateScreenConfig(screenConfig)
            }
        )
    }
}