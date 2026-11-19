package com.example.tiendadeportiva.data

import com.example.tiendadeportiva.model.*
import java.time.LocalDate

object DatosPrueba {

    // Categorías (Pantalla 2)
    val categoria = Categoria("C01", "Fútbol", "Equipamiento de fútbol", "Fútbol")
    val categoriaRunning = Categoria("C02", "Running", "Equipamiento para correr", "Running")
    val categoriaBasquet = Categoria("C03", "Básquet", "Equipamiento de básquet", "Básquet")
    val categoriaTenis = Categoria("C04", "Tenis", "Equipamiento de tenis", "Tenis")
    val categoriaGimnasio = Categoria("C05", "Gimnasio", "Equipamiento de gimnasio", "Gimnasio")
    val categoriaOutdoor = Categoria("C06", "Outdoor", "Equipamiento para actividades al aire libre", "Outdoor")

    // Productos (Pantalla 1)
    val balon = Balon(
        "P01", "Balón Adidas Tiro", "Adidas", "Fútbol",
        320.0, 12, 5, "PU"
    )

    val calzado = CalzadoDeportivo(
        "P02", "Zapatillas Nike Revolution", "Nike", "Running",
        550.0, 8, 42.0, "Unisex"
    )

    val balonBasquet = Balon(
        "P03", "Balón Spalding", "Spalding", "Básquet",
        360.0, 10, 7, "Cuero sintético"
    )

    val cliente = Cliente("CL01", "Mauri", "mauri@correo.com", "70000000")
    val proveedor = Proveedor("PR01", "Deportes Bolivia S.R.L.", "22000000", "ventas@deportesbolivia.com")

    val productos: List<ProductoDeportivo> = listOf(balon, calzado, balonBasquet)
    val categorias: List<Categoria> = listOf(
        categoria, categoriaRunning, categoriaBasquet,
        categoriaTenis, categoriaGimnasio, categoriaOutdoor
    )

    /** Prueba rápida del comportamiento del modelo (llámala desde un test o desde onCreate con Log). */
    fun probarModelo() {
        productos.forEach { println(it.descripcion()) }

        println("¿Hay 5 balones? ${balon.hayStock(5)}")
        println("Descontar 5: ${balon.descontarStock(5)}")
        println("Stock actual: ${balon.stockActual}")
        println("Precio inválido: ${balon.actualizarPrecio(-1.0)}")

        val carrito = Carrito()
        println("Agregar balón x2: ${carrito.agregar(balon, 2)}")
        println("Agregar calzado x1: ${carrito.agregar(calzado, 1)}")
        println("Total carrito: ${carrito.calcularTotal()}")
        println("Quitar P02: ${carrito.quitar("P02")}")
        println("Total tras quitar: ${carrito.calcularTotal()}")

        val items = listOf(ItemCarrito(balon, 2), ItemCarrito(calzado, 1))
        val pedido = Pedido("PD01", cliente, items, LocalDate.now())
        println("Estado inicial: ${pedido.estadoActual::class.simpleName}")
        pedido.cambiarEstado(EstadoPedido.Pagado)
        println("Estado actual: ${pedido.estadoActual::class.simpleName}")
        println("Total pedido: ${pedido.calcularTotal()}")

        cliente.actualizarContacto("nuevo@correo.com", "71111111")
        proveedor.actualizarDatos()
        listOf<Identificable>(categoria, balon, cliente, proveedor, pedido)
            .forEach { println("ID: ${it.obtenerId()}") }
    }
}
