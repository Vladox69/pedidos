package dev.android.apppedidos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import dev.android.apppedidos.R
import dev.android.apppedidos.entidades.ProductoCollectionItem
import kotlinx.android.synthetic.main.fila_producto_escoger.view.*

class ProductosAdapter(private val contexto:Context,private val listaProductos:List<ProductoCollectionItem>):
    ArrayAdapter<ProductoCollectionItem>(contexto,0,listaProductos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout=LayoutInflater.from(contexto).inflate(R.layout.fila_producto_escoger,parent,false)
        val productos=listaProductos[position]
        layout.textViewProductoEscogerNombre.setText(productos.nombre)
        layout.textViewProductoEscogerPrecio.setText(productos.precio.toString())
        return layout
    }
}