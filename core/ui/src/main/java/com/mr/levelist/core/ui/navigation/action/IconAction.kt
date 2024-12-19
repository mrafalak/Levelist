package com.mr.levelist.core.ui.navigation.action

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector

sealed class IconAction(
    val titleResId: Int,
    val descriptionResId: Int? = null,
    val icon: ImageVector,
    val onClick: () -> Unit
) {
    class Add(
        titleResId: Int,
        descriptionResId: Int? = null,
        onClick: () -> Unit,
    ) : IconAction(
        titleResId = titleResId,
        descriptionResId = descriptionResId,
        icon = Icons.Default.Add,
        onClick = onClick
    )
}