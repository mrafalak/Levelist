package com.mr.levelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mr.levelist.core.ui.theme.LevelistTheme
import com.mr.levelist.navigation.MainNavHost
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

            LevelistTheme {
                if (isLoading) {
                    Text("Loading...")
                } else {
                    // TODO isUserLogged
                    MainNavHost(isUserLoggedIn = true)
                }
            }
        }
    }
}