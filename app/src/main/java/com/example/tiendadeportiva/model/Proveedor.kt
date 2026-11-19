package com.example.tiendadeportiva.model

class Proveedor(
    val id: String,
    var razonSocial: String,
    var telefono: String,
    var correo: String
) : Identificable {

    // El modelo no define parámetros: se normalizan los datos ya almacenados.
    fun actualizarDatos() {
        razonSocial = razonSocial.trim()
        telefono = telefono.trim()
        correo = correo.trim()
    }

    override fun obtenerId(): String = id
}
