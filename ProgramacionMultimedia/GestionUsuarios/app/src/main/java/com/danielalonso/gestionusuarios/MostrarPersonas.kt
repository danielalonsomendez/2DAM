package com.danielalonso.gestionusuarios

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MostrarPersonas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mostrar_personas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtLista : TextView = findViewById(R.id.txtLista)
        val btnMostrar : Button = findViewById(R.id.buttonMostrar)
        txtLista.text = ListaUsuarios.usuarios.joinTo(StringBuilder(), separator = "\n")
        btnMostrar.setOnClickListener {
            finish()

        }
    }
}