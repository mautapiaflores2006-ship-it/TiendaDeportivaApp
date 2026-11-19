package com.example.tiendadeportiva.model

sealed class EstadoPedido {
    object Pendiente : EstadoPedido()
    object Pagado : EstadoPedido()
    object Enviado : EstadoPedido()
    object Entregado : EstadoPedido()
    object Cancelado : EstadoPedido()
}
