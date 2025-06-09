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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lockscreen.R
import com.example.lockscreen.core.Sizing
import com.example.lockscreen.core.Spacer
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
                .padding(Spacer.large)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.set_your_passcode), fontSize = Sizing.veryLarge)

        Spacer(modifier = Modifier.height(Spacer.medium))

        OutlinedTextField(
            value = state.passcode,
            onValueChange = {
                val numbersOnly = it.filter { char -> char.isDigit() }
                viewModel.onAction(StarterAction.OnPasscodeChange(numbersOnly))
            },
            label = { Text(stringResource(R.string.passcode)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(Spacer.medium))

        Button(
            onClick = {
                if (state.passcode.isNotBlank()) {
                    onNext(state.passcode)
                }
            }
        ) {
            Text(stringResource(R.string.next))
        }
    }
}
