package dev.android.apppedidos.entidades

class Producto:ArrayList<ProductoCollectionItem>()

data class ProductoCollectionItem(
    var producto_id:Int,
    var nombre: String,
    var precio:Double
)