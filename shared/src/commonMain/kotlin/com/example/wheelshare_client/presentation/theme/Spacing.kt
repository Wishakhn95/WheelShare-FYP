package com.example.wheelshare_client.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ExtendedSpacing (
    val xxs: Dp =2.dp,
    val xs: Dp = 4.dp,
    val s : Dp = 8.dp,
    val m : Dp = 12.dp,
    val semiLargeSpacing: Dp = 14.dp,
    val l : Dp = 16.dp,
    val xl : Dp = 20.dp,
    val xxl : Dp = 24.dp,
    val iconSmall : Dp = 20.dp,
    val pillIcon : Dp = 15.dp
)

val LocalWheelShareSpacing = compositionLocalOf {
    ExtendedSpacing()
}