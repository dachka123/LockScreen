package com.example.lockscreen.lock.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundColors(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Box(
            modifier = Modifier
                .size(180.dp)
                .offset(x = (-130).dp, y = (10).dp)
                .background(color = Color(0xFFFFFF00).copy(alpha = 0.2f), shape = CircleShape)
                .blur(1400.dp)
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .offset(x = (-80).dp, y = 800.dp)
                .background(color = Color(0xFFFF0000).copy(alpha = 0.2f), shape = CircleShape)
                .blur(1100.dp)
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .offset(x = 350.dp, y = 560.dp)
                .background(color = Color(0xFF00FF00).copy(alpha = 0.2f), shape = CircleShape)
                .blur(1100.dp)
        )
        content()
    }
}