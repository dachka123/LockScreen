package com.example.lockscreen.starter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class StarterViewModel: ViewModel() {

    var state by mutableStateOf(StarterState())
        private set


    fun onAction(action: StarterAction) {
        when (action) {
            is StarterAction.OnPasscodeChange -> {
                state = state.copy(passcode = action.value)
            }
        }
    }
}