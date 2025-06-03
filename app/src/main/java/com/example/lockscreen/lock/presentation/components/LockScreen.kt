package com.example.lockscreen.lock.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lockscreen.lock.presentation.LockViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lockscreen.R
import com.example.lockscreen.core.ui.theme.LockScreenTheme
import com.example.lockscreen.lock.presentation.LockAction


@Composable
fun LockScreen(
    viewModel: LockViewModel = viewModel(),
) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.security_screen),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp))

        PassCode(
            enteredCount = state.enteredDigits.length,
            totalCount = state.enteredDigits.length.coerceAtLeast(state.correctPasscode.length)
        )

        KeyPad(
            onDigitClick = { digit ->
                viewModel.onAction(LockAction.OnClick(digit.toInt()))
            },
            onBackspaceClick = {
                viewModel.onAction(LockAction.Clear)
            }
        )

        if (state.isComplete) {
            Spacer(modifier = Modifier.height(16.dp))
            if (state.isCorrect) {
                Text(stringResource(R.string.access_granted), color = Color.Green, fontSize = 18.sp)
            } else {
                Text(stringResource(R.string.wrong_passcode), color = Color.Red, fontSize = 18.sp)
            }
        }
    }
}

@Preview
@Composable
private fun LockScreenPreview() {
    LockScreenTheme {
        LockScreen()
    }
}