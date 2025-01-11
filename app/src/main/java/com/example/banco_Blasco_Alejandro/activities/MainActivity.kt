package com.example.banco_Blasco_Alejandro.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.banco_Blasco_Alejandro.R

import com.example.banco_Blasco_Alejandro.databinding.ActivityMainBinding
import com.example.banco_Blasco_Alejandro.pojo.Cliente

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val cliente = intent.getSerializableExtra("Cliente") as Cliente

        val bienvenida = "Bienvenido/a \n${cliente.getNombre()}"
        binding.idTextView.text = bienvenida

        binding.btnSalir.setOnClickListener{
            val salir = Intent(this, LoginActivity::class.java)
            startActivity(salir)
        }

        binding.btnCambiarContrasenya.setOnClickListener{
            val cambiarContrasenya = Intent(this, ChangePasswordActivity::class.java)
            cambiarContrasenya.putExtra("Cliente", cliente)
            startActivity(cambiarContrasenya)
        }

        binding.btnTransferencias.setOnClickListener{
            val transferencias = Intent(this, TransfersActivity::class.java)
            startActivity(transferencias)
        }

        binding.btnPosicionGlobal.setOnClickListener{
            val posicion = Intent(this, GlobalPositionActivity::class.java)
            posicion.putExtra("Cliente", cliente)
            startActivity(posicion)
        }

        binding.btnMovimientos.setOnClickListener{
            val movimientos = Intent(this, MovementsActivity::class.java)
            movimientos.putExtra("Cliente", cliente)
            startActivity(movimientos)
        }

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}