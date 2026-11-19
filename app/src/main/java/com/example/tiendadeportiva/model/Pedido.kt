package com.example.tiendadeportiva.model

import java.time.LocalDate

class Pedido(
    val id: String,
    val cliente: Cliente,
    val items: List<ItemCarrito>,
    val fecha: LocalDate,
    private var estado: EstadoPedido = EstadoPedido.Pendiente
) : Identificable {

    init {
        // Composición Pedido – ItemCarrito (1 a 1..*)
        require(items.isNotEmpty()) { "Un pedido debe tener al menos un ItemCarrito" }
    }

    /** Lectura del estado (solo lectura): el cambio sigue pasando por cambiarEstado(). */
    val estadoActual: EstadoPedido
        get() = estado

    fun calcularTotal(): Double = items.sumOf { it.subtotal() }

    fun cambiarEstado(nuevoEstado: EstadoPedido) {
        estado = nuevoEstado
    }

    override fun obtenerId(): String = id
}
