package com.example.lockscreen.starter.coponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.sp
import com.example.lockscreen.starter.StarterAction
import com.example.lockscreen.starter.StarterViewModel
import kotlinx.serialization.Serializable

@Serializable
object StarterScreenDestination

@Composable
fun StarterScreen(
    viewModel: StarterViewModel = viewModel(),
    onNext: (String) -> Unit
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.run {
            fillMaxSize()
                .padding(24.dp)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Set Your Passcode", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.passcode,
            onValueChange = {
                val numsOnly = it.filter { char -> char.isDigit() }
                viewModel.onAction(StarterAction.OnPasscodeChange(numsOnly))
            },
            label = { Text("Passcode") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (state.passcode.isNotBlank()) {
                    onNext(state.passcode)
                }
            }
        ) {
            Text("Next")
        }
    }
}
