package dev.android.apppedidos.entidades

class Pedido:ArrayList<PedidoCollectionItem>()

data class PedidoCollectionItem(
    var pedido_id:Int,
    var cliente_id:Int,
    var empleado_id:Int,
    var estado_pedido_id:Int,
    var nombre:String,
    var direccion_pedido:String,
    var fecha_creado:String
)