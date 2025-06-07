package com.example.lockscreen.starter

sealed interface StarterAction {
    data class OnPasscodeChange(val value: String) : StarterAction
}
