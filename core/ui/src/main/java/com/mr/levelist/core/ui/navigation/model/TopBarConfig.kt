package com.mr.levelist.core.ui.navigation.model

import com.mr.levelist.core.ui.navigation.action.IconAction
import com.mr.levelist.core.ui.navigation.action.NavigationAction

data class TopBarConfig(
    val title: String = "",
    val topBarNavAction: NavigationAction? = null,
    val topBarActions: List<IconAction> = emptyList(),
)