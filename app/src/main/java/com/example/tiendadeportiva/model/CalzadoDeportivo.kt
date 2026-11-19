package com.example.tiendadeportiva.model

class CalzadoDeportivo(
    id: String,
    nombre: String,
    marca: String,
    deporte: String,
    precio: Double,
    stock: Int,
    val talla: Double,
    val genero: String
) : ProductoDeportivo(id, nombre, marca, deporte, precio, stock) {

    override fun descripcion(): String =
        "$nombre ($marca) - Calzado de $deporte, Talla: $talla, Género: $genero"
}
