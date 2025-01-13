package com.example.banco_Blasco_Alejandro.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.example.banco_Blasco_Alejandro.pojo.Cliente
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente: Cliente = intent.getSerializableExtra("Cliente") as Cliente

        binding.btnCambiar.setOnClickListener{
            val contrasenya = binding.etContrasenya.text.toString()

            val contrasenyaRepertir = binding.etContrasenyaRepertir.text.toString()

            if (contrasenya.isEmpty() || contrasenyaRepertir.isEmpty()) {

                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()

            }else if (contrasenya == contrasenyaRepertir){
                cliente.setClaveSeguridad(contrasenyaRepertir)
                val resultado = mbo?.changePassword(cliente) ?: -1
                if (resultado == 1){
                    val cambiar = Intent(this, MainActivity::class.java)
                    cambiar.putExtra("Cliente", cliente)
                    startActivity(cambiar)
                } else if (resultado == 0){
                    Toast.makeText(this, "No se ha realizado el cambio de contraseña", Toast.LENGTH_SHORT).show()
                }
            } else {

                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()

            }

        }

        binding.btnCancelar.setOnClickListener{
            val cancelar = Intent(this, MainActivity::class.java)
            startActivity(cancelar)
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}