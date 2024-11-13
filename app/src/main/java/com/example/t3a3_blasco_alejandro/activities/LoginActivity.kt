package com.example.t3a3_blasco_alejandro.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.example.bancoapiprofe.pojo.Cliente
import com.example.t3a3_blasco_alejandro.R
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

            val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

            var cliente = Cliente()
            cliente.setNif(binding.etUsuario.text.toString())
            cliente.setClaveSeguridad(binding.etPassword.text.toString())


            if (binding.etUsuario.text.toString().isEmpty() || binding.etPassword.text.toString().isEmpty()) {

                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()

            } else {

                try {
                    val clienteLogueado = mbo?.login(cliente) ?: -1

                    if (clienteLogueado == -1) {
                        Toast.makeText(this, "El cliente no se encuentra registrado en la BD", Toast.LENGTH_LONG).show()
                    } else {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("Cliente", clienteLogueado)
                        startActivity(intent)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error al intentar iniciar sesión", Toast.LENGTH_LONG).show()
                }



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