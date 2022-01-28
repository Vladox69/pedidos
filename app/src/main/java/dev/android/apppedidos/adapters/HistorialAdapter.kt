package dev.android.apppedidos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import dev.android.apppedidos.R
import dev.android.apppedidos.entidades.PedidoCollectionItem
import kotlinx.android.synthetic.main.fila_historial.view.*

class HistorialAdapter(private val contexto: Context, private val listaPedidos:List<PedidoCollectionItem>):
    ArrayAdapter<PedidoCollectionItem>(contexto,0,listaPedidos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout= LayoutInflater.from(contexto).inflate(R.layout.fila_historial,parent,false)
        val pedidos=listaPedidos[position]
        layout.textViewHistorialNombre.setText(pedidos.nombre)
        layout.textViewHistorialFecha.setText(pedidos.fecha_creado)
        return layout
    }
}