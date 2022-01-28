package dev.android.apppedidos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import dev.android.apppedidos.fragments.HistorialFragment
import dev.android.apppedidos.fragments.PedidosFragment
import kotlinx.android.synthetic.main.activity_principal.*

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        //opcionesPrincipal()
        val historialFragment=HistorialFragment()
        val pedidosFragment=PedidosFragment()

        makeCurrentFragment(pedidosFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_historial->makeCurrentFragment(historialFragment)
                R.id.ic_pedidos->makeCurrentFragment(pedidosFragment)
            }
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater=menuInflater
        inflater.inflate(R.menu.nav_header,menu)
        return true
    }
/*
    private fun opcionesPrincipal(){
        buttonPedidos.setOnClickListener(){
            val intent=Intent(this,PedidosActivity::class.java)
            startActivity(intent)
        }
    }*/

    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wreapper,fragment)
        commit()
    }

}