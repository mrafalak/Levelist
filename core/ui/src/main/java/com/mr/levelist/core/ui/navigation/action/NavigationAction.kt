package com.mr.levelist.core.ui.navigation.action

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.mr.levelist.core.ui.R

sealed class NavigationAction(
    val titleResId: Int,
    val descriptionResId: Int? = null,
    val icon: ImageVector,
    val onClick: () -> Unit
) {

    class Menu(
        onClick: () -> Unit,
    ) : NavigationAction(
        titleResId = R.string.ic_nav_menu_title,
        descriptionResId = R.string.ic_nav_menu_title,
        icon = Icons.Filled.Menu,
        onClick = onClick
    )
}