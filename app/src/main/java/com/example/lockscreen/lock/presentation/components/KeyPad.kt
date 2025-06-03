package com.example.lockscreen.lock.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lockscreen.core.ui.theme.LockScreenTheme

@Composable
fun KeyPad(
    onDigitClick: (String) -> Unit,
    onBackspaceClick: () -> Unit
) {
    val keypadRows = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "⌫")
    )

    LazyColumn {
        items(keypadRows) { row ->
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(row) { label ->
                    if (label.isEmpty()) {
                        Spacer(modifier = Modifier.size(75.dp))
                    } else {
                        Box(
                            modifier = Modifier
                                .size(75.dp)
                                .background(Color.White, shape = CircleShape)
                                .clickable {
                                    if (label == "⌫") {
                                        onBackspaceClick()
                                    } else {
                                        onDigitClick(label)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (label == "⌫") {
                                Icon(
                                    imageVector = Icons.Default.Backspace,
                                    contentDescription = "Backspace",
                                    tint = Color.Black
                                )
                            } else {
                                Text(text = label, fontSize = 24.sp)
                            }
                        }
                    }
                }
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
