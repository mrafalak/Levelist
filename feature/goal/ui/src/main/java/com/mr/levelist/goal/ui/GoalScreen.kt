package com.mr.levelist.goal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mr.levelist.core.ui.ScreenWithConfig
import com.mr.levelist.core.ui.navigation.model.TopBarState
import com.mr.levelist.core.ui.navigation.model.ScreenConfig
import com.mr.levelist.core.ui.navigation.model.TopBarConfig
import com.mr.levelist.feature.goal.ui.R

@Composable
fun GoalScreen(
    setScreenConfig: (ScreenConfig) -> Unit
) {
    val topBarConfig = TopBarConfig(title = stringResource(R.string.title_goals))

    ScreenWithConfig(
        setScreenConfig = setScreenConfig,
        screenConfig = ScreenConfig(
            topBarState = TopBarState.Visible(topBarConfig),
            isDrawerMenuIconEnabled = true,
            isDrawerMenuGestureEnabled = true,
            bottomBarVisible = true
        )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text("Goal screen")
        }
    }
}