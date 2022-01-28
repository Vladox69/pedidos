package dev.android.apppedidos.interfaces

import dev.android.apppedidos.entidades.ProductoCollectionItem
import retrofit2.Call
import retrofit2.http.GET

interface ProductoService {
    @GET("api/productos")
    fun listaProductos(): Call<List<ProductoCollectionItem>>
}