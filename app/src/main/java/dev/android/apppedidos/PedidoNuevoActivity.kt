package dev.android.apppedidos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pedido_nuevo.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class ProductosPedido(var producto:String,var cantidad:Int,var precio:Double)

class PedidoNuevoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_nuevo)
        cargarLista()
        cargarHora()
        cargarSubTotal()
        onClickButtonAgregar()
        onClickOpcionesPedidoNuevo()
    }

    var productosPedido= arrayListOf(
        ProductosPedido("Huevos de avestruz",5,10.5),
        ProductosPedido("Tomates",6,5.5),
        ProductosPedido("Naranjas",8,7.5),
        ProductosPedido("Pimientos",9,8.5),
    )

    private fun cargarLista(){
        val adaptador=Adaptador(this,productosPedido)
        lstProductos.adapter=adaptador
    }

    private fun calculoSubTotalPedido():Double{
        var acumulador:Double=0.0;

        for (producto in productosPedido){
            acumulador+=producto.precio
        }

        return acumulador
    }

    private fun cargarHora(){
        val sdf=SimpleDateFormat("dd/M/yyyy")
        val currenData=sdf.format(Date())
        textViewPedidoNuevoFecha.setText(currenData.toString())
    }

    private fun onClickButtonAgregar(){
        buttonPedidoAgregar.setOnClickListener(){
            val intent=Intent(this,ProductosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onClickOpcionesPedidoNuevo(){
        buttonPedidoAceptar.setOnClickListener(){
            val intent=Intent(this,PrincipalActivity::class.java)
            startActivity(intent)
        }

        buttonPedidoCancelar.setOnClickListener(){
            val intent=Intent(this,PrincipalActivity::class.java)
            startActivity(intent)
        }

    }

    private fun cargarSubTotal(){
        val subtotal=calculoSubTotalPedido()
        textViewSubTotal.setText(subtotal.toString())
    }

    private class Adaptador(contexto:Context,datos:ArrayList<ProductosPedido>):BaseAdapter(){

        val datosProductosPedido=datos
        val ctx=contexto

        private inner class ViewHolder(){
            internal var vhProductosPedidoNombre:TextView?=null
            internal var vhProductosPedidoCantidad:TextView?=null
            internal var vhProductosPedidoPrecio:TextView?=null
        }

        override fun getCount(): Int {
            return datosProductosPedido.size
        }

        override fun getItem(position: Int): Any {
            return datosProductosPedido[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, row: View?, parent: ViewGroup?): View {
            var viewHolder:ViewHolder
            var rowView=row

            if(rowView==null){
                viewHolder=ViewHolder()
                val inflater=LayoutInflater.from(ctx)
                rowView=inflater.inflate(R.layout.fila_producto,parent,false)
                viewHolder.vhProductosPedidoNombre=rowView.findViewById(R.id.textViewProductoNombre) as TextView
                viewHolder.vhProductosPedidoCantidad=rowView.findViewById(R.id.textViewProductoCantidad) as TextView
                viewHolder.vhProductosPedidoPrecio=rowView.findViewById(R.id.textViewProductoPrecio) as TextView

                rowView.tag=viewHolder

            }else{
                viewHolder=rowView.tag as ViewHolder
            }

            viewHolder.vhProductosPedidoNombre!!.setText(datosProductosPedido.get(position).producto)
            viewHolder.vhProductosPedidoCantidad!!.setText(datosProductosPedido.get(position).cantidad.toString())
            viewHolder.vhProductosPedidoPrecio!!.setText(datosProductosPedido.get(position).precio.toString())

            return rowView!!

        }

    }


}