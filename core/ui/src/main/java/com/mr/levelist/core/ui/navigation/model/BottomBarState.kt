package com.mr.levelist.core.ui.navigation.model

data class BottomBarState(
    val items: List<BottomBarItemConfig> = emptyList(),
    val selectedItem: BottomBarItemConfig? = null,
    val visible: Boolean = false,
)