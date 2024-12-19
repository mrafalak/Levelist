package com.mr.levelist.core.ui.navigation

import com.mr.levelist.core.ui.navigation.model.TopBarState
import com.mr.levelist.core.ui.navigation.action.NavigationAction
import com.mr.levelist.core.ui.navigation.model.ScreenConfig

fun ScreenConfig.addDrawerMenuIconIfPossible(onClick: () -> Unit): ScreenConfig {
    val topBarState = this.topBarState

    return if (topBarState is TopBarState.Visible) {
        val topBarConfigWithDrawerMenu =
            topBarState.config.copy(topBarNavAction = NavigationAction.Menu(onClick))

        this.copy(
            topBarState = TopBarState.Visible(topBarConfigWithDrawerMenu),
            isDrawerMenuIconEnabled = true
        )
    } else {
        this
    }
}