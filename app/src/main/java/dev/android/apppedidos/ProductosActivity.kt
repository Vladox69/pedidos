package dev.android.apppedidos


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import dev.android.apppedidos.adapters.ProductosAdapter
import dev.android.apppedidos.entidades.ProductoCollectionItem
import dev.android.apppedidos.interfaces.ProductoService
import dev.android.apppedidos.interfaces.RestEngine
import kotlinx.android.synthetic.main.activity_pedido_nuevo.*
import kotlinx.android.synthetic.main.activity_productos.*
import kotlinx.android.synthetic.main.fragment_pedidos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductosActivity : AppCompatActivity() {

    lateinit var adaptador:ProductosAdapter
     val listaProductos = mutableListOf<ProductoCollectionItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)
        cargarProductos()
        opcionesProductos()
        callServiceGetProductos()
    }
    private fun opcionesProductos(){

        buttonAceptarProductos.setOnClickListener(){
            val intent =Intent(this,PedidoNuevoActivity::class.java)
            startActivity(intent)
        }

        buttonCancelarProductos.setOnClickListener(){
            val intent = Intent(this,PedidoNuevoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun cargarProductos(){
        adaptador=ProductosAdapter(this,listaProductos)
        lstProductosEscoger.adapter=adaptador
    }

    private fun callServiceGetProductos(){
        var productos= emptyList<ProductoCollectionItem>()

        val productoService: ProductoService = RestEngine.getRestEngine().create(ProductoService::class.java)
        val result: Call<List<ProductoCollectionItem>> = productoService.listaProductos()
        result.enqueue(object : Callback<List<ProductoCollectionItem>> {
            override fun onResponse(
                call: Call<List<ProductoCollectionItem>>,
                response: Response<List<ProductoCollectionItem>>
            ) {
                Toast.makeText(this@ProductosActivity,"ok",Toast.LENGTH_LONG).show()
                productos= response.body()!!
                for ( item in productos){
                    listaProductos.add(item)
                }
                adaptador.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<List<ProductoCollectionItem>>, t: Throwable) {
                Toast.makeText(this@ProductosActivity,"error",Toast.LENGTH_LONG).show()
            }

        })
    }
}