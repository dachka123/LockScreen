package com.example.lockscreen.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lockscreen.core.ui.theme.LockScreenTheme
import com.example.lockscreen.lock.presentation.components.BackgroundColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LockScreenTheme {
                BackgroundColors()
            }
        }
    }
}