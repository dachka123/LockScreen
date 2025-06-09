package com.example.lockscreen.lock.presentation

sealed interface LockAction {
    data class OnClick(val digit: Int) : LockAction
    data object DeleteLast : LockAction
}