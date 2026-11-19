package com.example.tiendadeportiva.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tiendadeportiva.model.Carrito
import com.example.tiendadeportiva.model.Categoria
import kotlinx.coroutines.launch

/** Pantalla 2: listado de Categoria (al tocar una, vuelve a Productos filtrado por su deporte). */
@Composable
fun CategoriasScreen(
    categorias: List<Categoria>,
    carrito: Carrito,
    alVolver: () -> Unit,
    alSeleccionar: (Categoria) -> Unit,
    alIrAInicio: () -> Unit
) {
    var consulta by rememberSaveable { mutableStateOf("") }
    val snackbarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val visibles = categorias.filter { it.nombre.contains(consulta, ignoreCase = true) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BarraSuperior(
                titulo = "Categorías",
                consulta = consulta,
                alCambiarConsulta = { consulta = it },
                iconoNavegacion = Icons.AutoMirrored.Filled.ArrowBack,
                descripcionNavegacion = "Volver",
                alNavegar = alVolver
            )
        },
        bottomBar = {
            BarraInferior(
                alIrAInicio = alIrAInicio,
                alPulsarCarrito = {
                    scope.launch {
                        snackbarState.mostrar("Total del carrito: ${formatoBs(carrito.calcularTotal())}")
                    }
                },
                alPulsarPerfil = {
                    scope.launch { snackbarState.mostrar("Perfil: próximamente") }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) }
    ) { padding ->
        if (visibles.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay categorías para mostrar")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(padding),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(visibles, key = { it.obtenerId() }) { categoria ->
                    TarjetaCategoria(categoria = categoria, alPulsar = { alSeleccionar(categoria) })
                }
            }
        }
    }
}

@Composable
private fun TarjetaCategoria(categoria: Categoria, alPulsar: () -> Unit) {
    Card(
        onClick = alPulsar,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = iconoPorDeporte(categoria.deporte),
                    contentDescription = categoria.nombre,
                    modifier = Modifier.size(56.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = categoria.nombre,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
