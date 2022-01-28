package dev.android.apppedidos.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dev.android.apppedidos.PedidoNuevoActivity
import dev.android.apppedidos.R
import dev.android.apppedidos.adapters.PedidoAdapter
import dev.android.apppedidos.entidades.PedidoCollectionItem
import dev.android.apppedidos.interfaces.PedidoService
import dev.android.apppedidos.interfaces.RestEngine
import kotlinx.android.synthetic.main.fragment_pedidos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PedidosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pedidos, container, false)
    }
    lateinit var adaptador: PedidoAdapter
    val listaPedidos = mutableListOf<PedidoCollectionItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listaPedidos.clear()
        cargarLista()
        onClickButtonNuevoPedido()
        callServiceGetPedidos()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun cargarLista(){
        adaptador=PedidoAdapter(requireContext(),listaPedidos)
        lstPedidos.adapter=adaptador
    }

    private fun onClickButtonNuevoPedido(){
        btnNuevoPedido.setOnClickListener(){
            val intent=Intent(requireContext(),PedidoNuevoActivity::class.java)
            startActivity(intent)
        }
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
                Toast.makeText(requireContext(),"ok", Toast.LENGTH_LONG).show()
                pedidos= response.body()!!

                for ( item in pedidos){
                    listaPedidos.add(item)
                }
                adaptador.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<List<PedidoCollectionItem>>, t: Throwable) {
                Toast.makeText(requireContext(),"error", Toast.LENGTH_LONG).show()
            }

        })
    }

}