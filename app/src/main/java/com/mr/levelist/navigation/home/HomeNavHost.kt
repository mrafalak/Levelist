package com.mr.levelist.navigation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mr.levelist.core.ui.navigation.model.BottomBarState
import com.mr.levelist.core.ui.navigation.model.BottomBarItemConfig
import com.mr.levelist.core.ui.navigation.model.ScreenConfig
import com.mr.levelist.goal.navigation.GoalRoute
import com.mr.levelist.goal.navigation.addGoalNavigation
import com.mr.levelist.habit.navigation.HabitRoute
import com.mr.levelist.habit.navigation.addHabitNavigation
import com.mr.levelist.navigation.NavigationViewModel
import com.mr.levelist.settings.navigation.addSettingsNavigation
import com.mr.levelist.ui.LevelistBottomBar
import com.mr.levelist.ui.LevelistTopBar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeNavHost(
    rootNavController: NavHostController,
    nestedNavController: NavHostController,
    navigationViewModel: NavigationViewModel = koinViewModel()
) {
    val screenState = navigationViewModel.homeScreenConfig.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val updateScreenConfig: (ScreenConfig) -> Unit = { screenConfig ->
        navigationViewModel.updateScreenConfig(screenConfig)
    }

    val onClickBottomBarItem: (BottomBarItemConfig) -> Unit = { item ->
        nestedNavController.navigate(item.route) {
            screenState.value.bottomBarSelectedItem?.route?.let {
                popUpTo(it) { inclusive = true }
                launchSingleTop = true
            }
        }

        navigationViewModel.updateBottomBarSelectedItem(item)
    }

    val openDrawer: () -> Unit = {
        scope.launch { drawerState.open() }
    }

    val closeDrawer: () -> Unit = {
        scope.launch { drawerState.close() }
    }

    val items = listOf(
        BottomBarItemConfig(
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            description = "HabitT",
            route = HabitRoute.HabitList.route,
            onClick = onClickBottomBarItem
        ),
        BottomBarItemConfig(
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            description = "GoalT",
            route = GoalRoute.Goals.route,
            onClick = onClickBottomBarItem
        ),
    )

    // TODO move bottom bar items configuration to ViewModel?
    LaunchedEffect(true) {
        navigationViewModel.updateBottomBarItems(items)
        navigationViewModel.updateBottomBarSelectedItem(items[0])
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                navController = nestedNavController,
                onCloseDrawer = closeDrawer
            )
        },
        gesturesEnabled = screenState.value.screenConfig.isDrawerMenuGestureEnabled
    ) {
        Scaffold(
            topBar = {
                LevelistTopBar(topBarState = screenState.value.screenConfig.topBarState)
            },
            bottomBar = {
                with(screenState) {
                    LevelistBottomBar(
                        state = BottomBarState(
                            items = value.bottomBarItems,
                            selectedItem = value.bottomBarSelectedItem,
                            visible = value.screenConfig.bottomBarVisible
                        )
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = nestedNavController,
                startDestination = items[0].route,
                modifier = Modifier
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding()
                    )
                    .padding(horizontal = innerPadding.calculateLeftPadding(LayoutDirection.Ltr))
            ) {
                addHabitNavigation(nestedNavController, updateScreenConfig, openDrawer)
                addGoalNavigation(nestedNavController, updateScreenConfig, openDrawer)
                addSettingsNavigation(nestedNavController, updateScreenConfig)
            }
        }
    }
}