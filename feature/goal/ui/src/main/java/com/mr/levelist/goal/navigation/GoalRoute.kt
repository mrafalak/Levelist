package com.mr.levelist.goal.navigation

import com.mr.levelist.core.ui.navigation.AppRoute
import kotlinx.serialization.Serializable

sealed interface GoalRoute : AppRoute {
    @Serializable
    data object Goals : GoalRoute {
        override val route: String = "goal_list"
    }
}