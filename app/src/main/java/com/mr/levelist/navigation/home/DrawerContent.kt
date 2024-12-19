package com.mr.levelist.navigation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mr.levelist.settings.navigation.SettingsRoute

@Composable
fun DrawerContent(
    navController: NavHostController,
    onCloseDrawer: () -> Unit
) {
    // TODO design drawer content, dynamically add items
    ModalDrawerSheet {
        Text("Drawer title", modifier = Modifier.padding(16.dp))
        HorizontalDivider()
        NavigationDrawerItem(
            label = { Text(text = "Settings") },
            selected = false,
            onClick = {
                onCloseDrawer()
                navController.navigate(SettingsRoute.Settings.route)
            }
        )
    }
}