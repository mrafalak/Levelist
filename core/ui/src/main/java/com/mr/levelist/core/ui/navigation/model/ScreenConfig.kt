package com.mr.levelist.core.ui.navigation.model

data class ScreenConfig(
    val topBarState: TopBarState = TopBarState.Invisible,
    val isDrawerMenuIconEnabled: Boolean = false,
    val isDrawerMenuGestureEnabled: Boolean = false,
    val bottomBarVisible: Boolean = false,
)