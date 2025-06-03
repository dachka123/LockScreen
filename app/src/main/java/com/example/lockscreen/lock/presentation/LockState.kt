package com.example.lockscreen.lock.presentation

data class LockState(
    val enteredDigits: String = "",
    val isComplete: Boolean = false,
    val isCorrect: Boolean = false,
    val correctPasscode: String = "0934"
)
