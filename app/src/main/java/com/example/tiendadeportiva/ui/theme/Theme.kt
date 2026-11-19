package com.example.tiendadeportiva.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EsquemaClaro = lightColorScheme(
    primary = Color(0xFF0B4FA8),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD9E6FF),
    onPrimaryContainer = Color(0xFF001B3E),
    secondaryContainer = Color(0xFFDDE8FA),
    background = Color(0xFFF4F7FC),
    surface = Color.White,
    surfaceVariant = Color(0xFFE8EEF8)
)

@Composable
fun TiendaDeportivaTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = EsquemaClaro, content = content)
}
