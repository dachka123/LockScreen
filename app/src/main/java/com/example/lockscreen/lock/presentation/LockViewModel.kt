package com.example.lockscreen.lock.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class LockViewModel : ViewModel() {

    var state = MutableStateFlow(LockState())
        private set

    var incorrect = MutableStateFlow<String?>(null)
        private set

    fun setCorrectPasscode(passcode: String) {
        state.update { it.copy(correctPasscode = passcode) }
    }

    fun onAction(action: LockAction) {
        when (action) {
            is LockAction.OnClick -> {
                val current = state.value
                if (current.enteredDigits.length < current.correctPasscode.length) {
                    val updated = current.enteredDigits + action.digit
                    val isComplete = updated.length == current.correctPasscode.length
                    val isCorrect = isComplete && updated == current.correctPasscode

                    if (isComplete && !isCorrect) {
                        state.value = LockState(correctPasscode = current.correctPasscode)
                    } else {
                        state.update {
                            it.copy(
                                enteredDigits = updated,
                                isComplete = isComplete,
                                isCorrect = isCorrect
                            )
                        }
                    }
                }
            }

            is LockAction.DeleteLast -> {
                state.update {
                    it.copy(
                        enteredDigits = it.enteredDigits.dropLast(1),
                        isComplete = false,
                        isCorrect = false
                    )
                }
            }

            is LockAction.Error -> {
                incorrect.value = null
            }
        }
    }
}