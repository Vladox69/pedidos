package dev.android.apppedidos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.android.apppedidos.entidades.PedidoCollectionItem
import dev.android.apppedidos.entidades.ProductoCollectionItem
import dev.android.apppedidos.interfaces.PedidoService
import dev.android.apppedidos.interfaces.ProductoService
import dev.android.apppedidos.interfaces.RestEngine
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        opcionesMain()
    }

    private fun opcionesMain(){
        onClickButtonEntrar()
        onClickButtonRegistroAct()
    }

    private fun onClickButtonEntrar(){
        buttonEntrar.setOnClickListener(){
            val intent=Intent(this,PrincipalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onClickButtonRegistroAct(){
        buttonRegistroAct.setOnClickListener(){

            //val intent=Intent(this,RegistrarseActivity::class.java)
            //startActivity(intent)
            //callServiceGetProductos()
            //callServiceGetPedidos()
            //callServiceCreatePedido()
        }
    }

    private fun callServiceGetProductos(){
        val productoService:ProductoService=RestEngine.getRestEngine().create(ProductoService::class.java)
        val result: Call<List<ProductoCollectionItem>> = productoService.listaProductos()
        result.enqueue(object :Callback<List<ProductoCollectionItem>>{
            override fun onResponse(
                call: Call<List<ProductoCollectionItem>>,
                response: Response<List<ProductoCollectionItem>>
            ) {
               Toast.makeText(this@MainActivity,"ok",Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<List<ProductoCollectionItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"error",Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun callServiceCreatePedido(){
        val pedido:PedidoCollectionItem= PedidoCollectionItem(0,1,5,2,"Testing kotlin","Test","2022-01-24T00:00:00")
        val pedidosService: PedidoService = RestEngine.getRestEngine().create(PedidoService::class.java)
        val result: Call<PedidoCollectionItem> = pedidosService.registrarPedido(pedido)
        result.enqueue(object : Callback<PedidoCollectionItem> {
            override fun onResponse(
                call: Call<PedidoCollectionItem>,
                response: Response<PedidoCollectionItem>
            ) {
                Toast.makeText(this@MainActivity,"creado", Toast.LENGTH_LONG).show()
            }
            override fun onFailure(call: Call<PedidoCollectionItem>, t: Throwable) {
                Toast.makeText(this@MainActivity,"error", Toast.LENGTH_LONG).show()
            }
        })
    }


   private fun callServiceGetPedidos(){
        val pedidosService: PedidoService = RestEngine.getRestEngine().create(PedidoService::class.java)
        val result: Call<List<PedidoCollectionItem>> = pedidosService.listaPedidos()
        result.enqueue(object : Callback<List<PedidoCollectionItem>> {
            override fun onResponse(
                call: Call<List<PedidoCollectionItem>>,
                response: Response<List<PedidoCollectionItem>>
            ) {
                Toast.makeText(this@MainActivity,"ok", Toast.LENGTH_LONG).show()
            }
            override fun onFailure(call: Call<List<PedidoCollectionItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"error", Toast.LENGTH_LONG).show()
            }
        })
    }


}