package com.example.wheelshare_client.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun WheelShareTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }

    CompositionLocalProvider(
        // Provide the extended colors based on the darkTheme parameter.
        LocalWheelShareColors provides if (darkTheme) darkExtendedColors else lightExtendedColors,
        // Provide the custom shapes defined by markazShapes.
        LocalWheelShareShapes provides markazShapes,
        // Provide the custom spacing values.
        LocalWheelShareSpacing provides ExtendedSpacing(),
    ) {
        // Apply the MaterialTheme with the custom typography and the content provided in the composable.
        MaterialTheme(
            colors = colors,
            typography = getTypoGraphy(),
            content = content
        )
    }


}

@Composable
private fun getTypoGraphy(): Typography {
    return Typography(
        h1 =
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        ),
        h2 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        ),
        h3 =
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        ),
        h4 =
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        ),
        h5 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        ),
        h6 =
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
        ),
        subtitle1 =
        TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
        ),
        subtitle2 =
        TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        ),
        body1 =
        TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        ),
        button =
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        ),
        caption =
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
        ),
        overline =
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
        )
    )

}