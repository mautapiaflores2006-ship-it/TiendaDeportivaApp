package com.example.tiendadeportiva.model

abstract class ProductoDeportivo(
    val id: String,
    var nombre: String,
    var marca: String,
    var deporte: String,
    var precio: Double,
    private var stock: Int
) : Identificable {

    /** Lectura del stock (solo lectura): la escritura sigue encapsulada. */
    val stockActual: Int
        get() = stock

    fun actualizarPrecio(nuevoPrecio: Double): Boolean {
        if (nuevoPrecio <= 0.0) return false
        precio = nuevoPrecio
        return true
    }

    fun hayStock(cantidad: Int): Boolean = cantidad > 0 && stock >= cantidad

    fun descontarStock(cantidad: Int): Boolean {
        if (!hayStock(cantidad)) return false
        stock -= cantidad
        return true
    }

    override fun obtenerId(): String = id

    abstract fun descripcion(): String
}
