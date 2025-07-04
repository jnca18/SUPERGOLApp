package com.example.supergol.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.Shapes
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val GreenFutbol = Color(0xFF1EB980)

private val LightColorPalette = lightColors(
    primary = GreenFutbol,
    primaryVariant = GreenFutbol,
    secondary = GreenFutbol,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val AppTypography = Typography()
private val AppShapes = Shapes()

@Composable
fun SupergolTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}




