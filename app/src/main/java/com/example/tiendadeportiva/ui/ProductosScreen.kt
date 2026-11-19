package com.example.tiendadeportiva.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.tiendadeportiva.model.Balon
import com.example.tiendadeportiva.model.CalzadoDeportivo
import com.example.tiendadeportiva.model.Carrito
import com.example.tiendadeportiva.model.ProductoDeportivo
import kotlinx.coroutines.launch

enum class FiltroTipo(val etiqueta: String) {
    TODOS("Todos"),
    BALONES("Balones"),
    CALZADO("Calzado")
}

/** Pantalla 1: listado de ProductoDeportivo y sus subclases. */
@Composable
fun ProductosScreen(
    productos: List<ProductoDeportivo>,
    deporteFiltro: String?,
    carrito: Carrito,
    alAbrirCategorias: () -> Unit,
    alIrAInicio: () -> Unit
) {
    var filtro by rememberSaveable { mutableStateOf(FiltroTipo.TODOS) }
    var consulta by rememberSaveable { mutableStateOf("") }
    val snackbarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val visibles = productos.filter { p ->
        val porTipo = when (filtro) {
            FiltroTipo.TODOS -> true
            FiltroTipo.BALONES -> p is Balon
            FiltroTipo.CALZADO -> p is CalzadoDeportivo
        }
        val porDeporte = deporteFiltro == null || p.deporte == deporteFiltro
        val porTexto = p.nombre.contains(consulta, ignoreCase = true) ||
            p.marca.contains(consulta, ignoreCase = true)
        porTipo && porDeporte && porTexto
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BarraSuperior(
                titulo = if (deporteFiltro != null) "Productos · $deporteFiltro" else "Productos",
                consulta = consulta,
                alCambiarConsulta = { consulta = it },
                iconoNavegacion = Icons.Filled.Menu,
                descripcionNavegacion = "Ver categorías",
                alNavegar = alAbrirCategorias
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
        Column(modifier = Modifier.padding(padding)) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(FiltroTipo.entries) { opcion ->
                    FilterChip(
                        selected = filtro == opcion,
                        onClick = { filtro = opcion },
                        label = { Text(opcion.etiqueta) }
                    )
                }
            }

            if (visibles.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No hay productos para mostrar")
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(visibles, key = { it.obtenerId() }) { producto ->
                        TarjetaProducto(
                            producto = producto,
                            alAgregar = {
                                val agregado = carrito.agregar(producto, 1)
                                scope.launch {
                                    snackbarState.mostrar(
                                        if (agregado) {
                                            "${producto.nombre} agregado · Total: ${formatoBs(carrito.calcularTotal())}"
                                        } else {
                                            "No hay stock suficiente de ${producto.nombre}"
                                        }
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TarjetaProducto(producto: ProductoDeportivo, alAgregar: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = iconoDe(producto),
                    contentDescription = producto.nombre,
                    modifier = Modifier.size(44.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${producto.deporte} • ${producto.marca}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.size(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = formatoBs(producto.precio),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    EtiquetaStock(producto.stockActual)
                }
            }
            FilledIconButton(onClick = alAgregar, enabled = producto.stockActual > 0) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar al carrito")
            }
        }
    }
}

@Composable
private fun EtiquetaStock(stock: Int) {
    val hay = stock > 0
    val fondo = if (hay) Color(0xFFDFF5E3) else MaterialTheme.colorScheme.errorContainer
    val texto = if (hay) Color(0xFF1B6B33) else MaterialTheme.colorScheme.onErrorContainer
    Surface(shape = RoundedCornerShape(50), color = fondo) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (hay) {
                Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(14.dp), tint = texto)
                Spacer(Modifier.width(2.dp))
            }
            Text(
                text = if (hay) "Stock: $stock" else "Sin stock",
                style = MaterialTheme.typography.labelMedium,
                color = texto
            )
        }
    }
}
