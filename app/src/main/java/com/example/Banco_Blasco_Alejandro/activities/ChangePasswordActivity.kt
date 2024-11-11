package com.example.Banco_Blasco_Alejandro.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_blasco_alejandro.R
import com.example.t3a3_blasco_alejandro.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCambiar.setOnClickListener{
            val contrasenya = binding.etContrasenya.text.toString()

            val contrasenyaRepertir = binding.etContrasenyaRepertir.text.toString()

            if (contrasenya.isEmpty() || contrasenyaRepertir.isEmpty()) {

                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()

            } else {
                val cambiar = Intent(this, MainActivity::class.java)
                startActivity(cambiar)
            }

        }

        binding.btnCancelar.setOnClickListener{
            val cancelar = Intent(this, MainActivity::class.java)
            startActivity(cancelar)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}