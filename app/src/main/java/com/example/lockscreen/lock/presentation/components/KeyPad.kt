package com.example.lockscreen.lock.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lockscreen.core.Sizing
import com.example.lockscreen.core.Spacer


sealed interface KeyPadKeys {
    data class Key(val key: Int) : KeyPadKeys
    data object BackSpace : KeyPadKeys
    data object Empty : KeyPadKeys
}

@Composable
fun KeyPad(
    onDigitClick: (String) -> Unit,
    onBackspaceClick: () -> Unit
) {
    val keypadRows = listOf(
        KeyPadKeys.Key(1),
        KeyPadKeys.Key(2),
        KeyPadKeys.Key(3),
        KeyPadKeys.Key(4),
        KeyPadKeys.Key(5),
        KeyPadKeys.Key(6),
        KeyPadKeys.Key(7),
        KeyPadKeys.Key(8),
        KeyPadKeys.Key(9),
        KeyPadKeys.Empty,
        KeyPadKeys.Key(0),
        KeyPadKeys.BackSpace,
    )

    LazyVerticalGrid(
        modifier = Modifier.wrapContentWidth(unbounded = false),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(3),
    ) {
        items(keypadRows) {
            when (it) {
                KeyPadKeys.BackSpace -> {
                    Box(
                        modifier = Modifier
                            .size(Spacer.seventyFive)
                            .aspectRatio(1f,true)
                            .clip(CircleShape)
                            .background(Color.White, shape = CircleShape)
                            .clickable {
                                onBackspaceClick()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Backspace,
                            contentDescription = "Backspace",
                            tint = Color.Black
                        )

                    }
                }

                KeyPadKeys.Empty -> {
                    Spacer(modifier = Modifier.size(Spacer.seventyFive).aspectRatio(1f,true))

                }

                is KeyPadKeys.Key -> {
                    Box(
                        modifier = Modifier
                            .size(Spacer.seventyFive)
                            .aspectRatio(1f,true)
                            .clip(CircleShape)
                            .background(Color.White, shape = CircleShape)
                            .clickable {
                                onDigitClick(it.key.toString())
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = it.key.toString(), fontSize = Sizing.extraLarge)
                    }
                }
            }

        }
    }
}

@Preview
@Preview(device = Devices.TABLET)
@Composable
private fun som() {
    KeyPad(
        onDigitClick = {},
        onBackspaceClick = {}

    )
}
