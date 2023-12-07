package com.example.wheelshare_client.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wheelshare_client.presentation.theme.LocalWheelShareColors
import com.example.wheelshare_client.presentation.theme.LocalWheelShareShapes

@Composable
fun DashedBorderCard(
    modifier: Modifier = Modifier,
    borderStrokeWidth: Dp = 4.dp,
    borderColor: Color = LocalWheelShareColors.current.grey,
    content: @Composable BoxScope.() -> Unit
) =
    Box(
        modifier =
        modifier.clip(LocalWheelShareShapes.current.cardShape)
            .background(color = LocalWheelShareColors.current.surface).drawBehind {
                drawRoundRect(
                    color = borderColor,
                    style =
                    Stroke(
                        width = borderStrokeWidth.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 20f)
                    )
                )
            },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
