package com.example.t3a3_blasco_alejandro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_blasco_alejandro.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSalir.setOnClickListener{
            finishAffinity()
        }

        binding.btnEntrar.setOnClickListener{

            val usuario = binding.etUsuario.text.toString()

            val contrasena = binding.etPassword.text.toString()

            if (usuario.isEmpty() || contrasena.isEmpty()) {

                // Mostrar un mensaje de error si algún campo está vacío
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()

            } else {

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("DNI", usuario)
                }

                startActivity(intent)
            }








        }


        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}