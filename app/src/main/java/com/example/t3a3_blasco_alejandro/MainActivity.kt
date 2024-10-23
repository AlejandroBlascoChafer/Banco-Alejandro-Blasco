package com.example.t3a3_blasco_alejandro

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_blasco_alejandro.databinding.ActivityLoginBinding
import com.example.t3a3_blasco_alejandro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val textoRecibido = intent.getStringExtra("DNI")


        binding.idTextView.text = textoRecibido

        binding.btnSalir.setOnClickListener{
            val salir = Intent(this, LoginActivity::class.java)
            startActivity(salir)
        }

        binding.btnCambiarContrasenya.setOnClickListener{
            val cambiarContrasenya = Intent(this, ChangePasswordActivity::class.java)
            startActivity(cambiarContrasenya)
        }

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}