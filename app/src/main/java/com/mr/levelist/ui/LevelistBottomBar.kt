package com.mr.levelist.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.mr.levelist.core.ui.navigation.model.BottomBarState
import com.mr.levelist.core.ui.UiConfig

@Composable
fun LevelistBottomBar(state: BottomBarState) {
    val animDuration = UiConfig.NAVIGATION_ANIM_DURATION

    val density = LocalDensity.current
    val insets = WindowInsets.navigationBars
    val navigationBarHeight = with(density) { insets.getBottom(density).toDp() }
    val bottomBarHeight = navigationBarHeight + 80.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = animDuration))
            .height(if (state.visible) bottomBarHeight else 0.dp)
    ) {
        AnimatedVisibility(
            visible = state.visible,
            enter = fadeIn(
                animationSpec = tween(durationMillis = animDuration)
            ) + slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(durationMillis = animDuration)
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = animDuration)
            ) + slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(durationMillis = animDuration)
            )
        ) {
            NavigationBar(modifier = Modifier.fillMaxSize()) {
                state.items.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                if (state.selectedItem == item) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.description
                            )
                        },
                        label = { Text(item.description) },
                        selected = state.selectedItem == item,
                        onClick = { item.onClick(item) }
                    )
                }
            }
        }
    }
}