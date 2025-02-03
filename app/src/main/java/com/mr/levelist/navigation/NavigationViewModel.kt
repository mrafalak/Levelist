package com.mr.levelist.navigation

import androidx.lifecycle.ViewModel
import com.mr.levelist.core.ui.navigation.model.BottomBarItemConfig
import com.mr.levelist.core.ui.navigation.model.HomeScreenConfig
import com.mr.levelist.core.ui.navigation.model.ScreenConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationViewModel : ViewModel() {

    private val _homeScreenConfig = MutableStateFlow(HomeScreenConfig())
    val homeScreenConfig: StateFlow<HomeScreenConfig> = _homeScreenConfig

    fun updateScreenConfig(config: ScreenConfig) {
        _homeScreenConfig.value = homeScreenConfig.value.copy(
            screenConfig = config
        )
    }

    fun updateBottomBarSelectedItem(item: BottomBarItemConfig) {
        _homeScreenConfig.value = homeScreenConfig.value.copy(
            bottomBarSelectedItem = item
        )
    }

    fun updateBottomBarItems(items: List<BottomBarItemConfig>) {
        _homeScreenConfig.value = homeScreenConfig.value.copy(
            bottomBarItems = items
        )
    }
}