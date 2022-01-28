package dev.android.apppedidos.interfaces

import dev.android.apppedidos.entidades.PedidoCollectionItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PedidoService {
    @GET("api/pedidos")
    fun listaPedidos(): Call<List<PedidoCollectionItem>>

    @POST("api/pedidos")
    fun registrarPedido(@Body params:PedidoCollectionItem):Call<PedidoCollectionItem>

}