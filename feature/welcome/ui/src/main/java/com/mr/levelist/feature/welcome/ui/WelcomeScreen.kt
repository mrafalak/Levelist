package com.mr.levelist.feature.welcome.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WelcomeScreen(navigateToLogIn: () -> Unit) {
    Column {
        Text("Welcome screen")
        Button(
            onClick = navigateToLogIn
        ) {
            Text(text = "Welcome")
        }
    }
}