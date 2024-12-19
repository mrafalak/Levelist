package com.mr.levelist.core.ui.navigation.model

data class HomeScreenConfig(
    val screenConfig: ScreenConfig = ScreenConfig(),
    val bottomBarItems: List<BottomBarItemConfig> = emptyList(),
    val bottomBarSelectedItem: BottomBarItemConfig? = null
)