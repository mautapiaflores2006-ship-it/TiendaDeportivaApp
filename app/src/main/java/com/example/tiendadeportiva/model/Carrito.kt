package com.example.tiendadeportiva.model

class Carrito {

    private val items: MutableList<ItemCarrito> = mutableListOf()

    fun agregar(producto: ProductoDeportivo, cantidad: Int): Boolean {
        if (cantidad <= 0) return false
        val existente = items.find { it.producto.obtenerId() == producto.obtenerId() }
        return if (existente != null) {
            existente.cambiarCantidad(existente.cantidad + cantidad)
        } else {
            if (!producto.hayStock(cantidad)) return false
            items.add(ItemCarrito(producto, cantidad))
        }
    }

    fun quitar(productoId: String): Boolean =
        items.removeAll { it.producto.obtenerId() == productoId }

    fun calcularTotal(): Double = items.sumOf { it.subtotal() }

    fun vaciar() {
        items.clear()
    }
}
