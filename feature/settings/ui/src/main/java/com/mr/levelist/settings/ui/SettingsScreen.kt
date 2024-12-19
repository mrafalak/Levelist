package com.mr.levelist.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mr.levelist.core.ui.ScreenWithConfig
import com.mr.levelist.core.ui.navigation.model.TopBarState
import com.mr.levelist.core.ui.navigation.model.ScreenConfig

@Composable
fun SettingsScreen(
    setScreenConfig: (ScreenConfig) -> Unit
) {
    ScreenWithConfig(
        setScreenConfig = setScreenConfig,
        screenConfig = ScreenConfig(
            TopBarState.Invisible
        )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text("Settings screen")
        }
    }
}