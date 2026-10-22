package com.koraglobal.app.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PitchEmerald,
    onPrimary = DeepNavyDark,
    primaryContainer = PitchEmeraldDark,
    secondary = AccentGold,
    background = DeepNavyDark,
    surface = NavySurfaceDark,
    surfaceVariant = NavyCardDark,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    outline = DividerDark
)

private val LightColorScheme = lightColorScheme(
    primary = PitchEmeraldDark,
    onPrimary = CardLight,
    primaryContainer = PitchEmeraldLight,
    secondary = AccentGold,
    background = SurfaceLight,
    surface = CardLight,
    surfaceVariant = SurfaceLight,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight,
    outline = DividerLight
)

@Composable
fun KoraGlobalTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
