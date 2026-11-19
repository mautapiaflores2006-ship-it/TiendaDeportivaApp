package com.example.tiendadeportiva.model

class ItemCarrito(
    val producto: ProductoDeportivo,
    var cantidad: Int
) {

    fun subtotal(): Double = producto.precio * cantidad

    fun cambiarCantidad(cantidad: Int): Boolean {
        if (cantidad <= 0 || !producto.hayStock(cantidad)) return false
        this.cantidad = cantidad
        return true
    }
}
