package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val appDarkColors = Colors(
    //Background colors
    primary          = Color(152,35,136),
    primaryVariant   = Color(0xFF3700B3),
    secondary        = Color(0xFF03DAC6),
    secondaryVariant = Color(0xFF03DAC6),
    background       = Color(0xFF121212),
    surface          = Color(0xFF121212),
    error            = Color(0xFFCF6679),

    //Typography and icon colors
    onPrimary        = Color.Black,
    onSecondary      = Color.Black,
    onBackground     = Color.White,
    onSurface        = Color.White,
    onError          = Color.Black,

    isLight = false
)

@Composable
fun AppTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors     = appDarkColors,
        typography = typography,
        shapes     = shapes,
        content    = content
    )
}