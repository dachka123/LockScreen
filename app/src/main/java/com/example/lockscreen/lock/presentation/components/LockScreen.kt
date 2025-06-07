package com.example.lockscreen.lock.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lockscreen.lock.presentation.LockViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lockscreen.R
import com.example.lockscreen.lock.presentation.LockAction
import kotlinx.serialization.Serializable

@Serializable
data class LockScreenDestination(
    val passcode: String
)

@Composable
fun LockScreen(
    passcode: String,
    viewModel: LockViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(passcode) {
        viewModel.setCorrectPasscode(passcode)
    }


    BackgroundColors {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
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
                totalCount = state.correctPasscode.length.coerceAtLeast(state.correctPasscode.length)
            )

            KeyPad(
                onDigitClick = { digit ->
                    viewModel.onAction(LockAction.OnClick(digit.toInt()))
                },
                onBackspaceClick = {
                    viewModel.onAction(LockAction.DeleteLast)
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
}
