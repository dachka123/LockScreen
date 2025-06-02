package com.example.lockscreen.lock.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LockViewModel: ViewModel() {

    private val _state = mutableStateOf(LockState())
    val state: State<LockState> = _state

    private val correctPasscode = listOf(0, 9, 3, 4)

    fun onAction(action: LockAction){
        when(action) {
            is LockAction.OnClick -> {
                if (_state.value.enteredDigits.size < 4) {
                    val current = _state.value.enteredDigits
                    if (current.size < 4) {
                        val updated = current + action.digit
                        val isComplete = updated.size == 4
                        val isCorrect = isComplete && updated == correctPasscode

                        _state.value = _state.value.copy(
                            enteredDigits = updated,
                            isComplete = isComplete,
                            isCorrect = isCorrect
                        )
                    }
                }
            }
            is LockAction.Clear -> {
                _state.value = LockState()
            }
        }
    }
}