package dev.android.apppedidos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import dev.android.apppedidos.R
import dev.android.apppedidos.entidades.PedidoCollectionItem
import kotlinx.android.synthetic.main.fila_pedido.view.*

class PedidoAdapter(private val contexto: Context, private val listaPedidos:List<PedidoCollectionItem>):
    ArrayAdapter<PedidoCollectionItem>(contexto,0,listaPedidos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout=LayoutInflater.from(contexto).inflate(R.layout.fila_pedido,parent,false)
        val pedidos=listaPedidos[position]
        layout.textViewPedidoNombre.setText(pedidos.nombre)
        layout.textViewPedidoEstado.setText(pedidos.estado_pedido_id.toString())
        layout.buttonPedidoAccion.setText("Ver")
        return layout
    }
}