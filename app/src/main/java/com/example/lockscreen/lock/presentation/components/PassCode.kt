package com.example.lockscreen.lock.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lockscreen.R
import com.example.lockscreen.core.Sizing
import com.example.lockscreen.core.Spacer

@Composable
fun PassCode(
    enteredCount: Int,
    totalCount: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = Spacer.extraLarge)
    ) {
        Text(
            text = stringResource(R.string.enter_your_passcode),
            fontSize = Sizing.medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = Spacer.medium)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Spacer.extraLarge),
            modifier = Modifier.padding(bottom = Spacer.veryLarge)
        ) {
            items(totalCount) { index ->
                val filled = index < enteredCount
                Box(
                    modifier = Modifier
                        .size(Spacer.mediumSmall)
                        .background(
                            color = if (filled) Color.Green else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}
