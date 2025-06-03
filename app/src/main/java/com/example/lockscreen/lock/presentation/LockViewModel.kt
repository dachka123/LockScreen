package com.example.lockscreen.lock.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LockViewModel: ViewModel() {

    var state by mutableStateOf(LockState())
        private set

    fun onAction(action: LockAction){
        when(action) {
            is LockAction.OnClick -> {
                if (state.enteredDigits.length < state.correctPasscode.length) {
                    val updated = state.enteredDigits + action.digit
                    val isComplete = updated.length == state.correctPasscode.length
                    val isCorrect = isComplete && updated == state.correctPasscode

                        state = state.copy(
                            enteredDigits = updated,
                            isComplete = isComplete,
                            isCorrect = isCorrect
                        )
                    }
                }
            is LockAction.Clear -> {
                state = LockState()
            }
        }
    }
}