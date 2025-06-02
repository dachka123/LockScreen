package com.example.lockscreen.lock.presentation

data class LockState(
    val enteredDigits: List<Int> = emptyList(),
    val isComplete: Boolean = false,
    val isCorrect: Boolean = false
)
