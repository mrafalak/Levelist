package com.mr.levelist.habit.navigation

import com.mr.levelist.core.ui.navigation.AppRoute
import kotlinx.serialization.Serializable

sealed interface HabitRoute : AppRoute {

    @Serializable
    data object HabitList : HabitRoute {
        override val route: String = "habit_list"
    }

    @Serializable
    data object CreateHabit : HabitRoute {
        override val route: String = "create_habit"
    }
}