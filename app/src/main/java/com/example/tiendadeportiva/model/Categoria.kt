package com.example.tiendadeportiva.model

class Categoria(
    val id: String,
    var nombre: String,
    var descripcion: String,
    var deporte: String
) : Identificable {

    override fun obtenerId(): String = id
}
