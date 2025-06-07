package com.example.lockscreen.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lockscreen.core.ui.theme.LockScreenTheme
import com.example.lockscreen.lock.presentation.components.LockScreen
import com.example.lockscreen.lock.presentation.components.LockScreenDestination
import com.example.lockscreen.starter.coponents.StarterScreen
import com.example.lockscreen.starter.coponents.StarterScreenDestination

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LockScreenTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "starterScreen"
                ) {
                    composable<StarterScreenDestination> {
                        StarterScreen(onNext = { passcode ->
                            navController.navigate(LockScreenDestination(passcode))
                        })
                    }

                    composable<LockScreenDestination> { backStackEntry ->
                        LockScreen(passcode = backStackEntry.passcode)
                    }
                }
            }
        }
    }
}

