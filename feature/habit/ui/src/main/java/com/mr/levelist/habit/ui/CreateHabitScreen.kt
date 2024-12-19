package com.mr.levelist.habit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mr.levelist.core.ui.navigation.model.TopBarState
import com.mr.levelist.core.ui.navigation.model.ScreenConfig

@Composable
fun CreateHabitScreen(
    setScreenConfig: (ScreenConfig) -> Unit
) {
    setScreenConfig(
        ScreenConfig(
            topBarState = TopBarState.Invisible,
        )
    )

    Column {
        Text("New habit screen")
    }
}