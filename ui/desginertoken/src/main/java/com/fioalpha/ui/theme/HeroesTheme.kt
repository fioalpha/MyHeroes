package com.fioalpha.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

class HeroesShapes(
    val small: CornerBasedShape = RoundedCornerShape(8.dp),
    val medium: CornerBasedShape = RoundedCornerShape(16.dp),
    val large: CornerBasedShape = RoundedCornerShape(46.dp)
)

internal val LocalShapes = staticCompositionLocalOf { HeroesShapes() }

@Composable
fun HeroesTheme(content: @Composable () -> Unit) {
    MaterialTheme { content() }
}