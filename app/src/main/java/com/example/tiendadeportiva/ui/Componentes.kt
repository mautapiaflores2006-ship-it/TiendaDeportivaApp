package com.example.tiendadeportiva.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.layout.fillMaxWidth
import com.example.tiendadeportiva.model.Balon
import com.example.tiendadeportiva.model.CalzadoDeportivo
import com.example.tiendadeportiva.model.ProductoDeportivo
import java.util.Locale

/** Barra superior con título y buscador (TopAppBar). */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(
    titulo: String,
    consulta: String,
    alCambiarConsulta: (String) -> Unit,
    iconoNavegacion: ImageVector,
    descripcionNavegacion: String,
    alNavegar: () -> Unit
) {
    var buscando by rememberSaveable { mutableStateOf(false) }
    val primario = MaterialTheme.colorScheme.primary
    val sobrePrimario = MaterialTheme.colorScheme.onPrimary

    TopAppBar(
        title = {
            if (buscando) {
                TextField(
                    value = consulta,
                    onValueChange = alCambiarConsulta,
                    placeholder = { Text("Buscar…") },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = sobrePrimario,
                        unfocusedTextColor = sobrePrimario,
                        cursorColor = sobrePrimario,
                        focusedPlaceholderColor = sobrePrimario.copy(alpha = 0.7f),
                        unfocusedPlaceholderColor = sobrePrimario.copy(alpha = 0.7f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(titulo)
            }
        },
        navigationIcon = {
            IconButton(onClick = alNavegar) {
                Icon(iconoNavegacion, contentDescription = descripcionNavegacion)
            }
        },
        actions = {
            IconButton(onClick = {
                if (buscando) alCambiarConsulta("")
                buscando = !buscando
            }) {
                Icon(
                    imageVector = if (buscando) Icons.Filled.Close else Icons.Filled.Search,
                    contentDescription = if (buscando) "Cerrar búsqueda" else "Buscar"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primario,
            titleContentColor = sobrePrimario,
            navigationIconContentColor = sobrePrimario,
            actionIconContentColor = sobrePrimario
        )
    )
}

/** Navegación inferior: Inicio, Carrito y Perfil. */
@Composable
fun BarraInferior(
    alIrAInicio: () -> Unit,
    alPulsarCarrito: () -> Unit,
    alPulsarPerfil: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = alIrAInicio,
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = alPulsarCarrito,
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = null) },
            label = { Text("Carrito") }
        )
        NavigationBarItem(
            selected = false,
            onClick = alPulsarPerfil,
            icon = { Icon(Icons.Filled.Person, contentDescription = null) },
            label = { Text("Perfil") }
        )
    }
}

suspend fun SnackbarHostState.mostrar(mensaje: String) {
    currentSnackbarData?.dismiss()
    showSnackbar(mensaje)
}

fun iconoPorDeporte(deporte: String): ImageVector = when (deporte) {
    "Fútbol" -> Icons.Filled.SportsSoccer
    "Básquet" -> Icons.Filled.SportsBasketball
    "Tenis" -> Icons.Filled.SportsTennis
    "Gimnasio" -> Icons.Filled.FitnessCenter
    "Running" -> Icons.Filled.DirectionsRun
    "Outdoor" -> Icons.Filled.Terrain
    else -> Icons.Filled.Checkroom
}

fun iconoDe(producto: ProductoDeportivo): ImageVector = when (producto) {
    is CalzadoDeportivo -> Icons.Filled.DirectionsRun
    is Balon -> iconoPorDeporte(producto.deporte)
    else -> iconoPorDeporte(producto.deporte)
}

fun formatoBs(monto: Double): String =
    if (monto % 1.0 == 0.0) "Bs ${monto.toInt()}"
    else "Bs ${String.format(Locale.US, "%.2f", monto)}"
