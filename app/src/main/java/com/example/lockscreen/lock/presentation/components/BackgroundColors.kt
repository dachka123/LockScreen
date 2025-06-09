package com.example.lockscreen.lock.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun BackgroundColors(
    content: @Composable () -> Unit
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawBlurredCircle(
            color = Color.Yellow,
            center = Offset(-90f,400f)
        )
        drawBlurredCircle(
            color = Color.Red,
            center = Offset(30f,2350f)
        )
        drawBlurredCircle(
            color = Color.Green,
            center = Offset(1200f,1650f)
        )
    }
    content()
}

fun DrawScope.drawBlurredCircle(
    color: Color,
    center: Offset,
    radius: Float = 300f
){
    this.drawRoundRect(
        brush = Brush.radialGradient(
            colors = listOf(
                color.copy(alpha = 0.7f),
                Color.Transparent
            ),
            center = center,
            radius = radius
        )
    )
}


@Preview(showBackground = true)
@Composable
private fun prev() {
    BackgroundColors({})

}