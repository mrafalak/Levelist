package com.mr.levelist.feature.welcome.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(navigateToHome: () -> Unit) {
    Column {
        Text("Login screen")
        Button(
            onClick = navigateToHome
        ) {
            Text(text = "Login")
        }
    }
}