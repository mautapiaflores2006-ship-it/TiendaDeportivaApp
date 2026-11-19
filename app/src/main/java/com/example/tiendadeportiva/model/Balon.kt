package com.example.tiendadeportiva.model

class Balon(
    id: String,
    nombre: String,
    marca: String,
    deporte: String,
    precio: Double,
    stock: Int,
    val tamano: Int,
    val material: String
) : ProductoDeportivo(id, nombre, marca, deporte, precio, stock) {

    override fun descripcion(): String =
        "$nombre ($marca) - Balón de $deporte, Tamaño: $tamano, Material: $material"
}
