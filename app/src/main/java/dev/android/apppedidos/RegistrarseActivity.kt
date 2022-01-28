package dev.android.apppedidos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registrarse.*

class RegistrarseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        opcionesRegistro()
    }

    private fun opcionesRegistro(){
        onClickButtonRegistrarse()
        onClickButtonCancelar()
    }

    private fun onClickButtonRegistrarse(){
        buttonRegistrarse.setOnClickListener(){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onClickButtonCancelar(){
        buttonCancelar.setOnClickListener(){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

}