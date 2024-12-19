package com.mr.levelist.settings.navigation

import com.mr.levelist.core.ui.navigation.AppRoute
import kotlinx.serialization.Serializable

sealed interface SettingsRoute : AppRoute {
    @Serializable
    data object Settings : SettingsRoute {
        override val route: String = "settings"
    }
}