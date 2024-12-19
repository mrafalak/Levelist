package com.mr.levelist.core.ui.navigation.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItemConfig(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val description: String,
    val route: String,
    val onClick: (BottomBarItemConfig) -> Unit
)