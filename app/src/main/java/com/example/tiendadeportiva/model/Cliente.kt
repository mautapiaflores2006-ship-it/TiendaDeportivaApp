package com.example.tiendadeportiva.model

class Cliente(
    val id: String,
    var nombre: String,
    var correo: String,
    var telefono: String
) : Identificable {

    fun actualizarContacto(correo: String, telefono: String) {
        this.correo = correo
        this.telefono = telefono
    }

    override fun obtenerId(): String = id
}
