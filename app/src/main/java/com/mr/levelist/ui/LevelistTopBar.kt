package com.mr.levelist.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mr.levelist.core.ui.navigation.model.TopBarState
import com.mr.levelist.core.ui.UiConfig
import com.mr.levelist.core.ui.navigation.model.TopBarConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelistTopBar(
    modifier: Modifier = Modifier,
    topBarState: TopBarState,
) {
    val animDuration = UiConfig.NAVIGATION_ANIM_DURATION
    val visible = topBarState !is TopBarState.Invisible
    val topBarHeight = 76.dp

    val config: TopBarConfig = when (topBarState) {
        is TopBarState.Invisible -> TopBarConfig()
        is TopBarState.Visible -> topBarState.config
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = animDuration))
            .height(if (visible) topBarHeight else 0.dp)
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                animationSpec = tween(durationMillis = animDuration)
            ) + fadeIn(
                animationSpec = tween(durationMillis = animDuration)
            ),
            exit = slideOutVertically(
                animationSpec = tween(durationMillis = animDuration)
            ) + fadeOut(
                animationSpec = tween(durationMillis = animDuration)
            )
        ) {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor = MaterialTheme.colorScheme.onBackground,
                ),
                modifier = modifier,
                title = { Text(config.title) },
                navigationIcon = {
                    config.topBarNavAction?.let {
                        IconButton(onClick = it.onClick) {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.descriptionResId?.let { resId ->
                                    stringResource(resId)
                                }
                            )
                        }
                    }
                },
                actions = {
                    if (config.topBarActions.size > 3) {
                        var expanded by remember { mutableStateOf(false) }
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = null
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            config.topBarActions.forEach { option ->
                                DropdownMenuItem(
                                    text = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = option.icon,
                                                contentDescription = null
                                            )
                                            Text(
                                                modifier = Modifier.padding(8.dp),
                                                text = stringResource(id = option.titleResId)
                                            )
                                        }
                                    },
                                    onClick = {
                                        expanded = false
                                        option.onClick()
                                    }
                                )
                            }
                        }
                    } else {
                        config.topBarActions.forEach { action ->
                            IconButton(onClick = { action.onClick() }) {
                                Icon(
                                    imageVector = action.icon,
                                    contentDescription = action.descriptionResId?.let { resId ->
                                        stringResource(resId)
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}