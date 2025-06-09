package com.example.lockscreen.lock.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LockViewModel : ViewModel() {

    var state by mutableStateOf(LockState())
        private set

    fun setCorrectPasscode(passcode: String) {
        state = state.copy(correctPasscode = passcode)
    }

    fun onAction(action: LockAction) {
        when (action) {
            is LockAction.OnClick -> {
                val current = state
                if (current.enteredDigits.length < current.correctPasscode.length) {
                    val updated = current.enteredDigits + action.digit
                    val isComplete = updated.length == current.correctPasscode.length
                    val isCorrect = isComplete && updated == current.correctPasscode

                    if (isComplete && !isCorrect) {
                        state = LockState(correctPasscode = current.correctPasscode)
                    } else {
                        state = current.copy(
                            enteredDigits = updated,
                            isComplete = isComplete,
                            isCorrect = isCorrect
                        )
                    }
                }
            }

            is LockAction.DeleteLast -> {
                state = state.copy(
                    enteredDigits = state.enteredDigits.dropLast(1),
                    isComplete = false,
                    isCorrect = false
                )
            }

            /*is LockAction.Error -> {
                incorrect.value = null
            }*/
        }
    }
}