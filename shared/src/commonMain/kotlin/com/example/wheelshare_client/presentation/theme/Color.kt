package com.example.wheelshare_client.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Markaz material theme color palette.
 * Provides custom color variants that are not in the Material Theme.
 */

private val Green20 = Color(0XFFE3F4E7)
private val Green30 = Color(0xFFC7E5CE)
private val Green50 = Color(0xFF02BC87)
private val Green70 = Color(0xFF009578)
private val Green80 = Color(0xFF047861)

private val Red20 = Color(0xFFFFDCDC)
private val Red30 = Color(0xFFDBB5B5)
private val Red40 = Color(0xFFE57373)
private val Red50 = Color(0xFFEA5B5B)
private val Red70 = Color(0xFFFF002A)

private val Yellow20 = Color(0xFFFFE2C7)
private val Yellow30 = Color(0xFFD9B899)
private val Yellow50 = Color(0xFFFFBE21)
private val Yellow60 = Color(0xFFEDB120)

private val Blue20 = Color(0xFFD7F6FF)
private val Blue30 = Color(0xFFa9d7e4)
private val Blue50 = Color(0xFF009DCD)
private val Blue60 = Color(0xFF008bb5)
val DarkBlue50 = Color(0xFF070A56)
private val DarkBlue60 = Color(0xFF43468b)

private val YellowGreenSecondary = Color(0xFF6EF633)

private val Grey10 = Color(0xffffffff)
private val Grey20 = Color(0xfffcfcfc)
private val Grey30 = Color(0xfff3f4f5)
private val Grey40 = Color(0xffe5e5e5)
private val Grey50 = Color(0xffb5b4ba)
private val Grey60 = Color(0xff84858d)
private val Grey70 = Color(0xff3d3d3e)
private val Grey90 = Color(0xff1f1f1f)
private val Grey100 = Color(0xff161616)
private val Grey110 = Color(0xff0f0f0f)

private val darkBackground = Color(0xFF0E1821)
private val primaryYellow = Color(0xFFF7AB00)
private val onPrimary = Color(0xFF141D21)


@Immutable
data class ExtendedColors(
    val primaryVariantLight: Color,
    val primaryVariantLightBG: Color,
    val errorLight: Color,
    val primaryLight: Color,
    val primaryLightBG: Color,
    val greyLight: Color,
    val grey: Color,
    val greyMid: Color,
    val greyDark: Color,
    val greyBg: Color,
    val greyDarkText: Color,
    val warning: Color,
    val warningLight: Color,
    val primaryDark: Color,
    val youtube: Color,
    val secondarySurface: Color,
    val productViewBG: Color,
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
    val secondaryVariant: Color,
    val background: Color,
    val surface: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val error: Color,
    val onError: Color
)


/**
 * Custom color palette for the light theme.
 */
val lightExtendedColors =
    ExtendedColors(
        primary = primaryYellow,
        primaryVariant = Blue50,
        secondary = DarkBlue50,
        secondaryVariant = YellowGreenSecondary,
        background = darkBackground,
        surface = Grey10,
        onPrimary = onPrimary,
        onSecondary = Grey10,
        onBackground = Grey10,
        onSurface = DarkBlue50,
        error = Red50,
        onError = Grey10,
        primaryVariantLight = Blue20,
        primaryVariantLightBG = Blue20,
        errorLight = Red20,
        primaryLight = Green20,
        primaryLightBG = Green20,
        greyLight = Grey30,
        grey = Grey40,
        greyMid = Grey50,
        greyDark = Grey60,
        greyBg = Grey40,
        greyDarkText = Grey60,
        warningLight = Yellow20,
        warning = Yellow50,
        primaryDark = Green70,
        youtube = Red70,
        secondarySurface = Grey60,
        productViewBG = Grey110
    )

/**
 * Custom color palette for the dark theme.
 */


val darkExtendedColors =
    ExtendedColors(
        primaryVariantLight = Blue30,
        primaryVariantLightBG = Blue20,
        errorLight = Red20,
        primaryLight = Green30,
        primaryLightBG = Grey90,
        greyLight = Grey90,
        grey = Grey70,
        greyMid = Grey60,
        greyDark = Grey60,
        greyBg = Grey50,
        greyDarkText = Grey70,
        warning = Yellow60,
        warningLight = Yellow30,
        primaryDark = Green80,
        youtube = Red70,
        secondarySurface = Grey100,
        productViewBG = Grey110,
        primary = primaryYellow,
        primaryVariant = Blue60,
        secondary = DarkBlue60,
        secondaryVariant = YellowGreenSecondary,
        background = darkBackground,
        surface = Grey100,
        onPrimary = onPrimary,
        onSecondary = Grey40,
        onBackground = Grey10,
        onSurface = Grey40,
        error = Red40,
        onError = Grey40,
    )

/**
 * A [CompositionLocal] that provides the light theme color palette.
 */

val LocalWheelShareColors = staticCompositionLocalOf { lightExtendedColors }