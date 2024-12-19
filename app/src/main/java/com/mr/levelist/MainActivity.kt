package com.mr.levelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mr.levelist.core.ui.theme.LevelistTheme
import com.mr.levelist.navigation.MainNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LevelistTheme {
                // TODO isUserLogged
                MainNavHost(isUserLoggedIn = true)
            }
        }
    }
}