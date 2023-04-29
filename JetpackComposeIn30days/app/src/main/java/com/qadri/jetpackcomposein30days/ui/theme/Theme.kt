package com.qadri.jetpackcomposein30days.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    onSurface = md_theme_dark_surface ,
    secondary = md_theme_dark_secondary,
    background = md_theme_dark_background
)

private val LightColorPalette = lightColors(

    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    onSurface = md_theme_light_surface,
    secondary = md_theme_light_secondary,
    background = md_theme_light_background

)

@Composable
fun JetpackComposeIn30DaysTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}