package com.mr.levelist.core.ui.navigation.model

sealed class TopBarState {
    data object Invisible : TopBarState()
    data class Visible(
        val config: TopBarConfig = TopBarConfig(),
    ) : TopBarState()
}