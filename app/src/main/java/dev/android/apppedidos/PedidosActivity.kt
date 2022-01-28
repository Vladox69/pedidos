package dev.android.apppedidos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.android.apppedidos.adapters.PedidoAdapter
import dev.android.apppedidos.entidades.PedidoCollectionItem
import dev.android.apppedidos.interfaces.PedidoService
import dev.android.apppedidos.interfaces.RestEngine
import kotlinx.android.synthetic.main.activity_pedidos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PedidosActivity : AppCompatActivity() {
    lateinit var adaptador: PedidoAdapter
    val listaPedidos = mutableListOf<PedidoCollectionItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)
        cargarLista()
        callServiceGetPedidos()
    }

    private fun cargarLista(){
        adaptador=PedidoAdapter(this,listaPedidos)
        lstPedidosAct.adapter=adaptador
    }

    private fun callServiceGetPedidos(){
        var pedidos= emptyList<PedidoCollectionItem>()
        val pedidosService: PedidoService = RestEngine.getRestEngine().create(PedidoService::class.java)
        val result: Call<List<PedidoCollectionItem>> = pedidosService.listaPedidos()
        result.enqueue(object : Callback<List<PedidoCollectionItem>> {
            override fun onResponse(
                call: Call<List<PedidoCollectionItem>>,
                response: Response<List<PedidoCollectionItem>>
            ) {
                Toast.makeText(this@PedidosActivity,"ok", Toast.LENGTH_LONG).show()
                pedidos= response.body()!!

                for ( item in pedidos){
                    listaPedidos.add(item)
                }
                adaptador.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<List<PedidoCollectionItem>>, t: Throwable) {
                Toast.makeText(this@PedidosActivity,"error", Toast.LENGTH_LONG).show()
            }

        })
    }

}