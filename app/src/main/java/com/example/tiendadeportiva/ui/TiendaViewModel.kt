package com.example.tiendadeportiva.ui

import androidx.lifecycle.ViewModel
import com.example.tiendadeportiva.model.Carrito

/** Mantiene el Carrito vivo entre pantallas y rotaciones. */
class TiendaViewModel : ViewModel() {
    val carrito = Carrito()
}
