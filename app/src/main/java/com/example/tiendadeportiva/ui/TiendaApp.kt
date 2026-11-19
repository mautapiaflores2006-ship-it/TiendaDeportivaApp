package com.example.tiendadeportiva.ui

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tiendadeportiva.data.DatosPrueba

object Rutas {
    const val PRODUCTOS = "productos"
    const val ARG_DEPORTE = "deporte"
    const val PRODUCTOS_PATRON = "productos?deporte={deporte}"
    const val CATEGORIAS = "categorias"
}

private fun NavController.irAInicio() {
    navigate(Rutas.PRODUCTOS) {
        popUpTo(graph.findStartDestination().id)
        launchSingleTop = true
    }
}

@Composable
fun TiendaApp(viewModel: TiendaViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.PRODUCTOS_PATRON) {
        composable(
            route = Rutas.PRODUCTOS_PATRON,
            arguments = listOf(
                navArgument(Rutas.ARG_DEPORTE) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { entrada ->
            ProductosScreen(
                productos = DatosPrueba.productos,
                deporteFiltro = entrada.arguments?.getString(Rutas.ARG_DEPORTE),
                carrito = viewModel.carrito,
                alAbrirCategorias = { navController.navigate(Rutas.CATEGORIAS) },
                alIrAInicio = { navController.irAInicio() }
            )
        }

        composable(Rutas.CATEGORIAS) {
            CategoriasScreen(
                categorias = DatosPrueba.categorias,
                carrito = viewModel.carrito,
                alVolver = { navController.popBackStack() },
                alSeleccionar = { categoria ->
                    navController.navigate(
                        "${Rutas.PRODUCTOS}?${Rutas.ARG_DEPORTE}=${Uri.encode(categoria.deporte)}"
                    )
                },
                alIrAInicio = { navController.irAInicio() }
            )
        }
    }
}
