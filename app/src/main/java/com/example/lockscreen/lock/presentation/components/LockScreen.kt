package com.example.lockscreen.lock.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lockscreen.lock.presentation.LockViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lockscreen.core.ui.theme.LockScreenTheme
import com.example.lockscreen.lock.presentation.LockAction


@Composable
fun LockScreen(
    viewModel: LockViewModel = viewModel()
) {
    val state by viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Security Screen", fontSize = 20.sp)
        Text(
            text = "Enter your passCode",
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            repeat(4) { index ->
                val filled = index < state.enteredDigits.size
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(
                            color = if (filled) Color.Green else Color.White,
                            shape = CircleShape
                        )
                )
            }
        }

        Column {
            val rows = listOf(
                listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(7, 8, 9),
                listOf(null, 0, -1)
            )

            rows.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    row.forEach { num ->
                        if (num == null) {
                            Spacer(modifier = Modifier.size(64.dp))
                        } else {
                            val text = if (num == -1) "⌫" else num.toString()

                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(Color(0xFFE0E0E0), shape = CircleShape)
                                    .clickable {
                                        if (num == -1) {
                                            viewModel.onAction(LockAction.Clear)
                                        } else {
                                            viewModel.onAction(LockAction.OnClick(num))
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = text, fontSize = 24.sp)
                            }
                        }
                    }
                }
            }
        }

        if (state.isComplete) {
            Spacer(modifier = Modifier.height(16.dp))
            if (state.isCorrect) {
                Text("Access Granted", color = Color.Green, fontSize = 18.sp)
            } else {
                Text("Wrong Passcode", color = Color.Red, fontSize = 18.sp)
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